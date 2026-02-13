package auca.ac.rw.Assignment2.controller.menu;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import auca.ac.rw.Assignment2.model.menu.MenuItem;

@RestController
@RequestMapping("/api/menu")
public class MenuController {

    private List<MenuItem> menuItems = new ArrayList<>();

    public MenuController() {

        menuItems.add(new MenuItem(1L,"Burger","Beef burger",5000.0,"Main Course",true));
        menuItems.add(new MenuItem(2L,"Pizza","Cheese pizza",7700.5,"Main Course",true));
        menuItems.add(new MenuItem(3L,"Salad","Fresh salad",4600.0,"Appetize",true));
        menuItems.add(new MenuItem(4L,"Juice","Orange juice",3700.0,"Beverage",true));
        menuItems.add(new MenuItem(5L,"Coffee","Hot coffee",2499.5,"Beverage",true));
        menuItems.add(new MenuItem(6L,"Cake","Chocolate cake",4776.5,"Dessert",false));
        menuItems.add(new MenuItem(7L,"Ice Cream","Vanilla ice cream",3998.5,"Bevelage",true));
        menuItems.add(new MenuItem(8L,"Fries","Potato fries",2559.0,"Appetize",true));
    }

    // GET all
    @GetMapping
    public List<MenuItem> getAllMenuItems() {
        return menuItems;
    }

    // GET by ID
    @GetMapping("/{id}")
    public MenuItem getById(@PathVariable Long id) {

        for (MenuItem item : menuItems) {

            if (item.getId().equals(id)) {
                return item;
            }
        }

        return null;
    }

    // GET by category
    @GetMapping("/category/{category}")
    public List<MenuItem> getByCategory(@PathVariable String category) {

        List<MenuItem> result = new ArrayList<>();

        for (MenuItem item : menuItems) {

            if (item.getCategory().equalsIgnoreCase(category)) {
                result.add(item);
            }
        }

        return result;
    }

    // GET available items
    @GetMapping("/available")
    public List<MenuItem> getAvailable(@RequestParam boolean available) {

        List<MenuItem> result = new ArrayList<>();

        for (MenuItem item : menuItems) {

            if (item.isAvailable() == available) {
                result.add(item);
            }
        }

        return result;
    }

    // GET /api/menu/search?name
    @GetMapping("/search")
    public List<MenuItem> searchByName(@RequestParam String name) {

        List<MenuItem> result = new ArrayList<>();

        for (MenuItem item : menuItems) {
            if (item.getName().toLowerCase().contains(name.toLowerCase())) {
                result.add(item);
            }
        }

        return result;
    }

    // POST new item
    @PostMapping
    public MenuItem addItem(@RequestBody MenuItem item) {

        menuItems.add(item);

        return item;
    }

    // PUT /api/menu/{id}/availability 
    
    @PutMapping("/{id}/availability")
    public MenuItem toggleAvailability(@PathVariable Long id) {

        for (MenuItem item : menuItems) {

            if (item.getId().equals(id)) {
                item.setAvailable(!item.isAvailable());
                return item;
            }
        }

        return null;
    }

    // DELETE item
    @DeleteMapping("/{id}")
    public String deleteItem(@PathVariable Long id) {

        menuItems.removeIf(item -> item.getId().equals(id));

        return "Item deleted";
    }
}
