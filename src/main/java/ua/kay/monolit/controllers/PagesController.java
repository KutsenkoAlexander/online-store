package ua.kay.monolit.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import ua.kay.monolit.models.Pages;
import ua.kay.monolit.repositories.PagesRepositories;

import java.util.List;

@CrossOrigin
@RestController
public class PagesController {

    @Autowired
    PagesRepositories pagesRepositories;

    @RequestMapping("/page/all")
    public List<Pages> findAllPages() {
        return pagesRepositories.findAll();
    }

    @RequestMapping("/page/{url}")
    public Pages getPageData(@PathVariable("url") String url) {
        return pagesRepositories.findByUrl(url);
    }

    @RequestMapping("/page/single/{id}")
    public Pages findPageById(@PathVariable("id") Long id) {
        return pagesRepositories.findById(id);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @RequestMapping(value = "/rest/page/save", method = RequestMethod.POST)
    public Pages savePage(@RequestBody Pages pages){
        return pagesRepositories.saveAndFlush(pages);
    }

}
