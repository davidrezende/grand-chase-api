package br.com.gc.api.service;

import br.com.gc.api.GlobalConstants;
import br.com.gc.api.model.*;
import br.com.gc.api.repository.UserItemAttributeRepository;
import br.com.gc.api.repository.UserItemRepository;
import br.com.gc.api.repository.UserItemSocketRepository;
import br.com.gc.api.repository.UserItemStrengthRepository;
import br.com.gc.api.util.DateFormatSQLServer;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class UserItemService {
    public final UserItemRepository userItemRepository;
    public final UserItemSocketRepository userItemSocketRepository;
    public final UserItemAttributeRepository userItemAttributeRepository;
    public final UserItemStrengthRepository userItemStrengthRepository;

    public UserItem addItemUser(UserItem item) {
        log.info("Adding new item ::" + item.getItemID() + " for login ID::" + item.getLoginUID());
        item.setDelDateA(DateFormatSQLServer.format(new Date(), GlobalConstants.DATE_TIME_FORMAT));
        item.setWIGAUID("-1");
        item.setDelState(0);
        log.info(item.toString());
        return userItemRepository.save(item);
    }

    public UserItem lastItemUser(Integer itemID, Integer loginUID) throws Exception {
        log.info("Searching last item for this loginID:: " + loginUID + " and itemID::" + itemID);
        return userItemRepository.findFirstByItemIDAndLoginUIDOrderByItemUIDDesc(itemID, loginUID)
                .orElseThrow(() -> new Exception("Last item:: " + itemID + " added not found for this login :: " + loginUID));
    }

    public UserItemSocket addCardItemUser(UserItemSocket card) {
        log.info("Adding cardID::" + card.getCardID() + "in slotID::" + card.getSlotID()
                + " to itemUID::" + card.getItemUID() + " for this loginUID::" + card.getLoginUID());
        //  card.setSocketState(2); // 3 closed slot with card - 2 opened slot without card
        return userItemSocketRepository.save(card);
    }

    public UserItemAttribute addAttributeItemUser(UserItemAttribute attribute) {
        log.info("Adding attribute typeID::" + attribute.getTypeID() + " in slotID::" + attribute.getSlotID()
                + " to itemUID::" + attribute.getItemUID() + " for this loginUID::" + attribute.getLoginUID());
        // attribute.setTypeID(-1); // -1 is default for no attribute
        // attribute.setAttributeState(0); // 0 is default for no choose attribute
        return userItemAttributeRepository.save(attribute);
    }

    public UserItemStrength strengthenItemUser(UserItemStrength level) {
        log.info("Strengthening itemID:: " + level.getEquippedItemUID() + " with stone itemUID::"
                + level.getItemUID() + " for this level::" + level.getStrengthLevel()
                + " and this loginUID::" + level.getLoginUID());
        return userItemStrengthRepository.save(level);
    }

    public VoPanelItem addItemPanel(VoPanelItem item) throws Exception {
        log.info("Adding itemID::" + item.getItem().getItemID() + " from panel. Player::" + item.getItem().getLoginUID());
        UserItem addedItem = addItemUser(item.getItem());

        log.info("Adding attributes from this item");
        //if none attribute exists in vo create empty slots of attributes

        List<UserItemAttribute> attributes = new ArrayList<UserItemAttribute>();

        if(item.getAttributes() == null || item.getAttributes().isEmpty() ){
            for (int i=1; i< addedItem.getGradeID()+2; i++){
                UserItemAttribute attribute = new UserItemAttribute();
                attribute.setLoginUID(addedItem.getLoginUID());
                attribute.setItemUID(addedItem.getItemUID());
                attribute.setSlotID(i-1);
                attribute.setTypeID(-1);
                attribute.setValue(0);
                attribute.setAttributeState(0);
                attributes.add(attribute);
                if(addAttributeItemUser(attribute) == null){
                    throw  new Exception("An error occurred while inserting an attribute: "
                            + attribute.toString() + " to the item: "+ addedItem.toString());
                }
            }
            item.setAttributes(attributes);
        }


        log.info("Adding card slots from this item");
        List<UserItemSocket> cards = new ArrayList<UserItemSocket>();
        // if none card exists in vo create empty slots of cards
        if( item.getCards() == null || item.getCards().isEmpty()){
            for (int i = 1; i <= addedItem.getGradeID(); i++){
                UserItemSocket card = new UserItemSocket();
                card.setLoginUID(addedItem.getLoginUID());
                card.setItemUID(addedItem.getItemUID());
                card.setSlotID(i-1);
                card.setCardID(0);
                card.setSocketState(2);
                if(addCardItemUser(card) == null){
                    throw  new Exception("An error occurred while inserting an card: "
                            + card.toString() + " to the item: "+ addedItem.toString());
                }
                cards.add(card);
            }
            item.setCards(cards);
        }

        // if anything level strength exist in vo strengthen the item
        if(item.getLevelStrength() != null){
            log.info("Adding stone item for fortify the item");
            UserItem stoneItem = addItemUser(new UserItem(null,
                    addedItem.getLoginUID(),
                    62727,
                    2,
                    DateFormatSQLServer.format(new Date(), GlobalConstants.DATE_TIME_FORMAT),
                    0,
                    "-1"));

            UserItemStrength level = new UserItemStrength();
            level.setLoginUID(addedItem.getLoginUID());
            level.setItemUID(stoneItem.getItemUID());
            level.setEquippedItemUID(addedItem.getItemUID());
            level.setStrengthOrder(1);
            level.setStrengthLevel(item.getLevelStrength());

            log.info("Fortify this item from level::" + item.getLevelStrength());
            if(strengthenItemUser(level) == null){
                throw  new Exception("An error occurred while fortify "
                        + level.toString() + " with stone: "+ stoneItem.toString() + " to the item: "+ addedItem.toString());
            }
        }
        return item;
    }
}
