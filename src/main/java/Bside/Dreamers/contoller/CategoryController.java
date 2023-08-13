package Bside.Dreamers.contoller;

import Bside.Dreamers.domin.Category;
import Bside.Dreamers.service.CategoryService;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@RestController
@RequestMapping("/category")
public class CategoryController {

    @Resource(name = "categoryService")
    private CategoryService categoryService;

    @ApiOperation(value = "버킷 갯수에 따른 랜덤 카테고리")
    @PostMapping("/RandomSpin")
    public List RandomSpin(@RequestParam("num") Long num) throws Exception{

        List<Category> category = categoryService.findCategory();

        if (num > category.size()) {
            throw new IllegalArgumentException("랜덤 갯수가 카테고리의 갯수보다 많습니다.");
        }

        List extractedItems = new ArrayList();
        List copyOfItems = new ArrayList(category);
        Random random = new Random();

        for (int i = 0; i < num; i++) {
            int randomIndex = random.nextInt(copyOfItems.size());
            extractedItems.add(copyOfItems.remove(randomIndex));
        }

        return extractedItems;
    }


}
