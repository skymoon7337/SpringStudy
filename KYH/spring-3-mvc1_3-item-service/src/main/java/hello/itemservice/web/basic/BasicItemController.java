package hello.itemservice.web.basic;

import hello.itemservice.domain.item.Item;
import hello.itemservice.domain.item.ItemRepository;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/basic/items")
@RequiredArgsConstructor
public class BasicItemController {

    private final ItemRepository itemRepository;

    @GetMapping
    public String items(Model model) {
        List<Item> items = itemRepository.findAll();
        model.addAttribute("items", items);
        return "basic/items";
    }

    @GetMapping("/{itemId}")
    public String item(@PathVariable long itemId, Model model) {
        Item item = itemRepository.findById(itemId);
        model.addAttribute("item", item);
        return "basic/item"; // 저장확인 추가 -> 여기서 리다이렉트 받은걸 처리하게 추가
    }

    @GetMapping("/add")
    public String addForm() {
        return "basic/addForm";
    }

    //@PostMapping("/add")
    public String addItemV1(@RequestParam String itemName,
                       @RequestParam int price,
                       @RequestParam Integer quantity,
                       Model model) {

        Item item = new Item();
        item.setItemName(itemName);
        item.setPrice(price);
        item.setQuantity(quantity);

        itemRepository.save(item);

        model.addAttribute("item", item);

        return "basic/item";
    }

                              // 요청 파라미터 처리, Model 추가
    //@PostMapping("/add")  // (Item 객체 생성 후 요청 파라미터의 값을 프로퍼티 접근법(setxx)으로 입력
    public String addItemV2(@ModelAttribute("item") Item item, Model model) { //Model 생략 가능

        itemRepository.save(item);
//      model.addAttribute("item", item);  //자동 추가, 생략 가능

        return "basic/item";
    }

    //@PostMapping("/add")             //Item -> item이 model.addAttribute로 담긴다
    public String addItemV3(@ModelAttribute Item item) {
        itemRepository.save(item);
        return "basic/item";
    }
    // 브라우저는 현재 보이는 화면(HTML)을 새로고침하는 것이 아니라,
    // "이 화면을 가져오기 위해 마지막으로 보냈던 요청"을 다시 보낸다.
    //@PostMapping("/add")    //우리가 만든 객체들은 @ModelAttribute 생략 가능 (String,Integer 이런건 X)
    public String addItemV4(Item item) {
        itemRepository.save(item);
        return "basic/item";
    }

    //redirect를 통해 상세페이지로 Get요청을 보내게 해, F5를 해도 마지막 요청이 Get으로 들어가 새로운 객체 생성 X
    //@PostMapping("/add")
    public String addItemV5(Item item) {
        itemRepository.save(item);
        return "redirect:/basic/items/" + item.getId();
    }

    @PostMapping("/add")
    public String addItemV6(Item item, RedirectAttributes redirectAttributes) {
        Item savedItem = itemRepository.save(item);
        redirectAttributes.addAttribute("itemId", savedItem.getId());
        redirectAttributes.addAttribute("status", true); // 쿼리 파라미터 형식으로 들어감 (?status=true)
        return "redirect:/basic/items/{itemId}"; // @GetMapping("/{itemId}") public String item()에서 처리해야됨.
    }

    @GetMapping("/{itemId}/edit")
    public String editForm(@PathVariable Long itemId, Model model){
        Item item = itemRepository.findById(itemId);
        model.addAttribute("item", item);
        return "basic/editForm";
    }

    @PostMapping("/{itemId}/edit")
    public String edit(@PathVariable Long itemId, @ModelAttribute Item item){
        itemRepository.update(itemId,item);
        return "redirect:/basic/items/{itemId}";
    }

    // 테스트용 데이터 추가
    @PostConstruct
    public void init() {
        itemRepository.save(new Item("itemA", 10000, 10));
        itemRepository.save(new Item("itemB", 20000, 20));
    }
}
