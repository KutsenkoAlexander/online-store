package ua.kay.monolith.controller.api;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import ua.kay.monolith.model.Page;
import ua.kay.monolith.service.PageServiceImpl;

import java.util.stream.Stream;

@CrossOrigin
@RestController
@RequestMapping("/api/pages")
public class PageController {

    private PageServiceImpl pageService;

    public PageController(PageServiceImpl pageService) {
        this.pageService = pageService;
    }

    @GetMapping
    @ResponseBody
    public Stream<Page> findAll() {
        return pageService.findAll();
    }

    @GetMapping
    @ResponseBody
    public Page getPageData(@RequestParam("url") String url) {
        return pageService.findByUrl(url);
    }

    @GetMapping
    @ResponseBody
    public Page findPageById(@RequestParam("id:[\\\\d]+") Long id) {
        return pageService.findById(id);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping
    @ResponseBody
    public Page savePage(@RequestBody Page page){
        return pageService.save(page);
    }
}
