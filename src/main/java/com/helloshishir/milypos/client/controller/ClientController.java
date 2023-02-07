package com.helloshishir.milypos.client.controller;


import com.helloshishir.milypos.client.model.Client;
import com.helloshishir.milypos.client.service.ClientService;
import com.helloshishir.milypos.util.StorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;
import java.util.List;

@Controller
@RequestMapping("/clients")
public class ClientController {

    @Autowired
    ClientService clientService;


    @Autowired
    StorageService storageService;

    @GetMapping("index")
    public String index() {
        return "clients/index";
    }

    @GetMapping("getClientList")
    @ResponseBody
    public ResponseEntity<Page<Client>> getClientList(@RequestParam(value = "offset", defaultValue = "0", required = false) int offset,
                                                      @RequestParam(value = "limit", defaultValue = "10", required = false) int limit,
                                                      @RequestParam(value = "order", defaultValue = "ASC", required = false) String order,
                                                      @RequestParam(value = "sort", defaultValue = "id", required = false) String sort,
                                                      @RequestParam(value = "search", defaultValue = "", required = false) String searchQuery) {
        long pageNumber = (long) offset;
        long pageSize = (long) limit;

        if (pageNumber != 0 && pageSize != 0) {
            pageNumber = pageNumber / pageSize;
        }
        return new ResponseEntity<>(clientService.getClient(Math.toIntExact(pageNumber), Math.toIntExact(pageSize), order, sort, searchQuery), HttpStatus.OK);
    }


    @GetMapping("/create")
    public String showCreateForm(ModelMap modelMap) {
        Client client = new Client();
        modelMap.put("client", client);
        return "clients/form";

    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable Integer id, ModelMap modelMap) {
        Client client = clientService.findById(id);
        modelMap.put("client", client);
        return "clients/form";
    }

    @PostMapping("/save")
    public String save(@ModelAttribute Client client,
                       BindingResult bindingResult,
                       ModelMap modelMap,
                       RedirectAttributes redirectAttributes,
                       @RequestParam("image") MultipartFile multipartFile) throws IOException {

        if (bindingResult.hasErrors()) {
            return "clients/form";
        }

        String fileName = "";

        if (!multipartFile.isEmpty()) {
            // get the file name
            fileName = StringUtils.cleanPath(multipartFile.getOriginalFilename());
            client.setPhoto(fileName);
        }
        Client saveClient = clientService.save(client);

        if (!multipartFile.isEmpty()) {
            // get upload directory
            // in our case we will save it to the static resource directory
            // that exists in our class path
            fileName = "client-" + saveClient.getId() + "-" + fileName;
            storageService.save(multipartFile, fileName);

            saveClient.setPhoto(fileName);
            clientService.save(saveClient);
        }


        redirectAttributes.addFlashAttribute("SUCCESS_MESSAGE", "Client saved successfully!");
        return "redirect:/clients/index";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Integer id, ModelMap modelMap,
                         RedirectAttributes redirectAttributes) {
        clientService.delete(id);
        redirectAttributes.addFlashAttribute("SUCCESS_MESSAGE", "Client deleted successfully!");

        return "redirect:/clients/index";

    }

}
