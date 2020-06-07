package ua.kay.monolith.controller.api;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ua.kay.monolith.model.Page;
import ua.kay.monolith.service.PageServiceImpl;
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

    @GetMapping
    @RequestMapping(params = "url")
    public Page getPageData(@RequestParam("url") String url) {
        return pageService.findByUrl(url);
    }

    @GetMapping
    @RequestMapping(params = "id")
    public Page findPageById(@RequestParam("id:[\\\\d]+") Long id) {
        return pageService.findById(id);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping
    public Page savePage(@RequestBody Page page){
        return pageService.save(page);
    }
}
