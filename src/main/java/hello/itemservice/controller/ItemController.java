package hello.itemservice.controller;

import hello.itemservice.domain.item.Item;
import hello.itemservice.repository.ItemRepository;
import hello.itemservice.web.form.ItemSaveForm;
import hello.itemservice.web.form.ItemUpdateForm;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/items")
public class ItemController {

    private final ItemRepository itemRepository;

    @GetMapping("/list")
    public String itemList(Model model) {

        List<Item> items = itemRepository.findAll();

        model.addAttribute("items", items);

        return "/items/list";
    }

    @GetMapping("/register")
    public String registerForm(Model model) {

        model.addAttribute("item", new Item());

        return "/items/register";
    }

    @PostMapping("/register")
    public String registerItem(
            @Validated @ModelAttribute("item") ItemSaveForm saveForm,
            BindingResult bindingResult,
            Model model,
            RedirectAttributes redirectAttributes) {

        // Validation
        String itemName = saveForm.getName();
        Integer itemPrice = saveForm.getPrice();
        Integer itemQuantity = saveForm.getQuantity();

        if (itemPrice != null && itemQuantity != null) {
            int totalPrice = itemPrice * itemQuantity;
            if (totalPrice < 10000) {
                bindingResult.reject("totalPriceMin", new Object[]{10000, totalPrice}, null);
            }
        }

        if (bindingResult.hasErrors()) {
            model.addAttribute("errors", bindingResult);
            return "/items/register";
        }

        // Business Logic
        Item item = new Item(itemName, itemPrice, itemQuantity);

        Item savedItem = itemRepository.save(item);

        redirectAttributes.addAttribute("itemId", savedItem.getId());

//        return "/read";
        return "redirect:/items/read/{itemId}";
    }

    @GetMapping("/read/{itemId}")
    public String readItem(@PathVariable Long itemId, Model model) {

        Item item = itemRepository.findOne(itemId);

        model.addAttribute("item", item);

        return "/items/read";
    }

    @GetMapping("/modify/{itemId}")
    public String modifyForm(@PathVariable Long itemId, Model model) {

        Item item = itemRepository.findOne(itemId);

        model.addAttribute("item", item);

        return "/items/modify";
    }

    @PostMapping("/modify")
    public String modify(
            @Validated @ModelAttribute ItemUpdateForm updateItem,
            BindingResult bindingResult,
            Model model,
            RedirectAttributes redirectAttributes) {

        Long itemId = updateItem.getId();
        String itemName = updateItem.getName();
        Integer itemPrice = updateItem.getPrice();
        Integer itemQuantity = updateItem.getQuantity();

        // Validation
        if (bindingResult.hasErrors()) {
            model.addAttribute("errors", bindingResult);
            return "/items/modify";
        }

        Item item = new Item(itemName, itemPrice, itemQuantity);
        item.setId(itemId);

        // Business Logic
        itemRepository.modify(item);

        redirectAttributes.addAttribute("itemId", updateItem.getId());

//        return "/read";
        return "redirect:/items/read/{itemId}";
    }


    @GetMapping("/delete/{itemId}")
    public String delete(@PathVariable Long itemId) {

        itemRepository.delete(itemId);

        return "redirect:/items/list";
    }
}
