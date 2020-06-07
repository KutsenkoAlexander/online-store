package ua.kay.online.store.controller.api;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ua.kay.online.store.model.Page;
import ua.kay.online.store.service.PageServiceImpl;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/api/pages")
public class PageController {

    private PageServiceImpl pageService;

    public PageController(PageServiceImpl pageService) {
        this.pageService = pageService;
    }

    @GetMapping
    public List<Page> findAll() {
        return pageService.findAll();
    }

    @GetMapping("/url")
    public Page getPageData(@RequestParam("url") String url) {
        return pageService.findByUrl(url);
    }

    @GetMapping("/id")
    public Page findPageById(@RequestParam("id") Long id) {
        return pageService.findById(id);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping
    public Page savePage(@RequestBody Page page){
        return pageService.save(page);
    }
}
