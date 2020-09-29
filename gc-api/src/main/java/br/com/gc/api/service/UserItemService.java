package br.com.gc.api.service;

import br.com.gc.api.constants.GlobalConstants;
import br.com.gc.api.model.*;
import br.com.gc.api.repository.*;
import br.com.gc.api.util.DateFormatSQLServer;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Calendar;
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
    public final UserItemDurationRepository userItemDurationRepository;
    public final UserItemPeriodRepository userItemPeriodRepository;

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

    public UserItemDuration stackItemUser(UserItemDuration stack) {
        log.info("Stacking itemUID:: " + stack.getItemUID() + " with amount::"
                + stack.getDuration());
        return userItemDurationRepository.save(stack);
    }

    public UserItemPeriod addPeriodItemUser(UserItemPeriod period) {
        log.info("Adding temporary itemUID:: " + period.getItemUID() + " with number of days::"
                + period.getPeriod());
        return userItemPeriodRepository.save(period);
    }

    public VoPanelItem addItemPanel(VoPanelItem item) throws Exception {
        log.info("Adding itemID::" + item.getItem().getItemID() + " from panel. Player::" + item.getItem().getLoginUID());
        UserItem addedItem = addItemUser(item.getItem());
        if (addedItem == null) {
            throw new Exception("An error occurred while inserting an item:: "
                    + item.getItem().toString());
        }

        if (item.getTimeItem() != null && item.getTimeItem() > 0 && item.getTimeItem() < 1000) {
            log.info("Adding temporary item::" + item.getItem().getItemID() + " from panel. Player::" + item.getItem().getLoginUID()
                    + ". Number of days::" + item.getTimeItem());
            UserItemPeriod periodItem = new UserItemPeriod();
            periodItem.setLoginUID(addedItem.getLoginUID());
            periodItem.setItemUID(addedItem.getItemUID());
            periodItem.setPeriod(item.getTimeItem());

            Calendar c = Calendar.getInstance();
            c.add(Calendar.DATE, +item.getTimeItem());

            periodItem.setEndDate(DateFormatSQLServer.format(c.getTime(), GlobalConstants.DATE_TIME_FORMAT));
            UserItemPeriod periodItemResponse = addPeriodItemUser(periodItem);
            if (periodItemResponse == null) {
                throw new Exception("An error occurred while inserting an period item:: "
                        + periodItem.toString());
            }
        }

        //if item have stack amount when
        //not insert attributes, slot cards or fortify item

        log.info("Adding stack item::" + item.getItem().getItemID() + " from panel. Player::" + item.getItem().getLoginUID()
                + ". Amount::" + item.getAmountStack());
        if (item.getAmountStack() != null && item.getAmountStack() > 0 && item.getAmountStack() < 1000) {
            UserItemDuration stackObject = new UserItemDuration();
            stackObject.setLoginUID(addedItem.getLoginUID());
            stackObject.setItemUID(addedItem.getItemUID());
            stackObject.setDuration(item.getAmountStack());
            UserItemDuration stackResponse = stackItemUser(stackObject);
            if (stackResponse == null) {
                throw new Exception("An error occurred while inserting an stack:: "
                        + stackObject.toString());
            }

        } else {

            //if none attribute exists in vo create empty slots of attributes

            if (item.getIsEquipment() != null && item.getIsEquipment()) {

                if (item.getIsAcessory() != null && !item.getIsAcessory()) {

                    log.info("Adding card slots from this item");
                    List<UserItemSocket> cards = new ArrayList<>();
                    // if none card exists in vo create empty slots of cards
                    if (item.getCards() == null || item.getCards().isEmpty()) {
                        for (int i = 1; i <= addedItem.getGradeID(); i++) {
                            UserItemSocket card = new UserItemSocket();
                            card.setLoginUID(addedItem.getLoginUID());
                            card.setItemUID(addedItem.getItemUID());
                            card.setSlotID(i - 1);
                            card.setCardID(0);
                            card.setSocketState(2);
                            if (addCardItemUser(card) == null) {
                                throw new Exception("An error occurred while inserting an card: "
                                        + card.toString() + " to the item: " + addedItem.toString());
                            }
                            cards.add(card);
                        }
                        item.setCards(cards);
                    }

                }

                log.info("Adding attributes from this item");
                List<UserItemAttribute> attributes = new ArrayList<>();
                if (item.getAttributes() == null || item.getAttributes().isEmpty()) {
                    if (item.getItem().getGradeID() != 0) { // for items with normal rarity not insert prop
                        for (int i = 1; i < addedItem.getGradeID() + 2; i++) {
                            UserItemAttribute attribute = new UserItemAttribute();
                            attribute.setLoginUID(addedItem.getLoginUID());
                            attribute.setItemUID(addedItem.getItemUID());
                            attribute.setSlotID(i - 1);
                            attribute.setTypeID(-1);
                            attribute.setValue(0);
                            attribute.setAttributeState(0);
                            attributes.add(attribute);
                            if (addAttributeItemUser(attribute) == null) {
                                throw new Exception("An error occurred while inserting an attribute: "
                                        + attribute.toString() + " to the item: " + addedItem.toString());
                            }
                        }
                        item.setAttributes(attributes);
                    }
                }

                // if anything level strength exist in vo strengthen the item
                if (item.getLevelStrength() != null && item.getLevelStrength() >= 0 && item.getLevelStrength() <= 17) {
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
                    if (strengthenItemUser(level) == null) {
                        throw new Exception("An error occurred while fortify "
                                + level.toString() + " with stone: " + stoneItem.toString() + " to the item: " + addedItem.toString());
                    }
                }
            }
        }
        return item;
    }

    public void removeItemUser(UserItem item) throws Exception {
        log.info("Remove item ::" + item.getItemID() + " for login ID::" + item.getLoginUID());
        userItemRepository.findByLoginUIDAndItemID(item.getLoginUID(), item.getItemID())
                .orElseThrow(() -> new Exception("Items with ID::" + item.getItemID() + "not founded for this loginUID:: " + item.getLoginUID()))
                .forEach(itemFounded -> {
                        log.info("Deleting item with UID::" + itemFounded.getItemUID() + " and ID::" + itemFounded.getItemID());
                        userItemRepository.delete(itemFounded);
                });
    }
}
