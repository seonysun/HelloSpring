package com.sist.web;
import java.util.*;
import com.sist.dao.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class RecipeController {
	@GetMapping("recipe/recipe.do")
	public String recipe_list(Model model) {
		String[] menu= {
				"밑반찬","탕","찌개","디저트","면","만두밥","죽","젓갈","장류","양념","양식","샐러드","스프","빵",
				"소고기","돼지고기","닭고기","육류","채소","해물","달걀","유제품","가공식품","밀가루","건어물","버섯",
				"볶음","끓이기","부침","조림","무침","비빔","튀김","회"
		};
		model.addAttribute("menu", menu);
		return "recipe/recipe";
	}
	
	@PostMapping("recipe/find.do")
	public String recipe_find(String[] menu, Model model) {
		String s="";
		for(String m:menu) {
			s+=m+"|";
		}
		s.substring(0, s.lastIndexOf("|"));
		return "recipe/find";
	}
}
