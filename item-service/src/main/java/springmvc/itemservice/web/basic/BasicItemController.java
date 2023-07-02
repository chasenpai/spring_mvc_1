package springmvc.itemservice.web.basic;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import springmvc.itemservice.domain.item.Item;
import springmvc.itemservice.domain.item.ItemRepository;

import java.util.List;

@Controller
@RequestMapping("/basic/items")
@RequiredArgsConstructor
public class BasicItemController {

    private final ItemRepository itemRepository;

    @GetMapping
    public String items(Model model){
        List<Item> items = itemRepository.findAll();
        model.addAttribute("items", items);
        return "basic/items";
    }

    @GetMapping("/{itemId}")
    public String item(@PathVariable Long itemId, Model model) {
        Item item = itemRepository.findById(itemId);
        model.addAttribute("item", item);
        return "basic/item";
    }

    @GetMapping("/add")
    public String addForm() {
        return "basic/addForm";
    }

    /**
     * @ModelAttribute
     * - 모델에 지정한 객체를 자동으로 넣어준다
     * - ModelAttribute 이름을 생력 시 클래스명으로 모델에 자동 추가
     * - 어노테이션 자체를 생략할 수 있다
     */
    @PostMapping("/add")
    public String addItem(@ModelAttribute Item item, Model model) {
        itemRepository.save(item);
        //model.addAttribute("item", item); //자동 추가, 생략 가능
        return "redirect:/basic/items/" + item.getId();
    }

    @GetMapping("/{itemId}/edit")
    public String editForm(@PathVariable Long itemId, Model model) {
        Item item = itemRepository.findById(itemId);
        model.addAttribute("item", item);
        return "basic/editForm";
    }

    @PostMapping("/{itemId}/edit")
    public String edit(@PathVariable Long itemId, @ModelAttribute Item item) {
        itemRepository.update(itemId, item);
        return "redirect:/basic/items/{itemId}"; //스프링은 redirect:/ 로 편리하게 리다이렉트를 지원한다. @PathVariable 의 값도 사용 가능
    }

    /**
     * 테스트용 데이터 추가
     */
    @PostConstruct
    public void init() {
        itemRepository.save(new Item("testA", 10000, 10));
        itemRepository.save(new Item("testB", 20000, 20));
    }

}
