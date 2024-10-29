package nhuquynh.demospringboot.controllers.admin;

import jakarta.validation.Valid;
import nhuquynh.demospringboot.entity.Category;
import nhuquynh.demospringboot.services.CategoryModel;
import nhuquynh.demospringboot.services.CategoryService;
import nhuquynh.demospringboot.services.impl.CategoryServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("admin/category")
public class CategoryController {
    @Autowired
    CategoryService categoryService;

    @RequestMapping("")
    public String all(Model model) {
        List<Category> list = categoryService.findAll();
        model.addAttribute("listcate", list);
        return "admin/category-list";
    }

    @GetMapping("/insert")
    public String add(Model model) {
        CategoryModel cate = new CategoryModel();
        model.addAttribute("cate", cate);
        return "admin/category-insert";
    }

    @PostMapping("/save")
    public ModelAndView saveOrUpdate(ModelMap model,
                                     @Valid @ModelAttribute("cate") CategoryModel categoryModel,
                                     BindingResult result) {
        if (result.hasErrors()) {
            // Nếu có lỗi validation nghĩa là khng binding được, quay lại trang thêm hoặc cập nhật
            System.out.println("Errors: " + result.getAllErrors());
            return new ModelAndView("admin/category-insert");
        }
        Category entity = new Category();
        //copy từ model sang entity
        BeanUtils.copyProperties(categoryModel, entity);
        //gọi hàm save từ service lên để thực hiện
        System.out.println(categoryModel);
        categoryService.save(entity);
        String mess=" ";
        if (categoryModel.getIsEdit()==true){
            mess=" Edit Category";
        }else{
            mess=" Add Category";
        }
        // Sau khi lưu hoặc cập nhật thành công, chuyển hướng đến danh sách categories
        model.addAttribute("message", mess);
        return new ModelAndView("forward:/admin/category", model);
    }

    @GetMapping("/edit/{id}")
    public ModelAndView edit(ModelMap model, @PathVariable ("id") Integer categoryID) {
        Optional<Category> optionalCategory = categoryService.findById(categoryID);
        CategoryModel cate = new CategoryModel();
        //kiểm tra sự tồn tại của category
        if (optionalCategory.isPresent()) {
            Category entity = optionalCategory.get();
            //copy từ entity sang model
            BeanUtils.copyProperties(entity, cate);
            cate.setIsEdit(true);
            //đẩy dữ liệu lên view
            model.addAttribute("cate", cate);
        //dùng chung với save
            return new ModelAndView("admin/category-insert", model);
        }
        model.addAttribute("mess", "Category not found");
        return new ModelAndView("forward:admin/category-list", model);
    }

    @GetMapping("/delete/{id}")
    public ModelAndView delete(ModelMap model, @PathVariable ("id") Integer categoryID) {
        categoryService.deleteById(categoryID);
        model.addAttribute("message", "Delete Category");
        return new ModelAndView("redirect:/admin/category", model);
    }

}
