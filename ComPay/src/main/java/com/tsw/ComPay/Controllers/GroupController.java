package com.tsw.ComPay.Controllers;

import com.tsw.ComPay.Models.GroupModel;
import com.tsw.ComPay.Services.GroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Arrays;
import java.util.List;

@Controller
public class GroupController {

    @Autowired
    private GroupService groupService;

    @GetMapping("/groups")
    public String showGroups(Model model) {
        // Creamos una lista de grupos con datos
        List<GroupModel> groups = Arrays.asList(
                new GroupModel("Apartment", "https://storage.googleapis.com/a1aa/image/XCkJBoQley1uWq3prH1oFpVZvg9ZInicHRo7A8pSelkrsEmTA.jpg", 25.00),
                new GroupModel("Vacation", "https://storage.googleapis.com/a1aa/image/QoHrxe9fcaj4UEXtveNxWl1B6AD7mfnACovKFSMd5ZQpySYOB.jpg", 50.00),
                new GroupModel("Birthday party", "https://storage.googleapis.com/a1aa/image/BKvv1izsfuSEQChO41XJaFNZnvdRRya8ClKUbm8fr1ZosEmTA.jpg", 75.00)
        );

        if(groups == null){
            int a = 2;
        }

        // Nos aseguramos de que la lista de grupos no sea nula
        if (groups == null || groups.isEmpty()) {
            // Si no hay grupos, pasamos una lista vacía o un mensaje de error
            model.addAttribute("groups", Arrays.asList());
        } else {
            model.addAttribute("groups", groups);
        }

        return "groups/groups-layout"; // Retornamos la vista principal
    }

    @GetMapping("/create-group")
    public String createGroup() {
        return "groups/create-group"; // Retorna la vista principal con los fragmentos
    }

    @GetMapping("/view-group")
    public String viewGroup(Model model) {
        return "groups/group-expense-layout"; // Retorna la vista principal con los fragmentos
    }
}
