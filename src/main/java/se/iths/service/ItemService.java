package se.iths.service;


import se.iths.entity.Item;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;

@Transactional // Talar om att det är en klass som inteagerar mer databasen.
public class ItemService {

    @PersistenceContext
    EntityManager entityManager;


    public Item createItem(Item item) {

        entityManager.persist(item);
        return item;

    }

    public Item updateItem(Item item) {
        entityManager.merge(item);
        return item;
    }

    public Item findItemById(Long id) {
        return entityManager.find(Item.class, id);
    }

    public List<Item> getAllItems() {
        //I står för Item , är det User skall man avända U istället
        return entityManager.createQuery("SELECT i from Item i ", Item.class).getResultList();

    }

    public void deleteItem(Long id) {
        Item itemToDelete = entityManager.find(Item.class, id);
        entityManager.remove(itemToDelete);
    }

    public Item patchItem(Long id, double price) {
        Item itemToUpdate = entityManager.find(Item.class, id);
        itemToUpdate.setPrice(price);
        return entityManager.merge(itemToUpdate);

    }


}
