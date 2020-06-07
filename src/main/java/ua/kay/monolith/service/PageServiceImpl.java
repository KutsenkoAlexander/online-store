package ua.kay.monolith.service;

import org.springframework.stereotype.Service;
import ua.kay.monolith.exception.PageNotFoundException;
import ua.kay.monolith.model.Page;
import ua.kay.monolith.repository.PageRepositories;

import java.util.List;

@Service
public class PageServiceImpl implements CrudService<Page> {

    private PageRepositories pageRepositories;

    public PageServiceImpl(PageRepositories pageRepositories) {
        this.pageRepositories = pageRepositories;
    }

    @Override
    public List<Page> findAll() {
        return pageRepositories.findAll();
    }

    @Override
    public Page findById(Long id) throws PageNotFoundException {
        return pageRepositories.findById(id).orElseThrow(() -> new PageNotFoundException("Page wasn't found by id: ", id));
    }

    public Page findByUrl(String url) {
        return pageRepositories.findByUrl(url).orElseThrow(() ->  new PageNotFoundException("Page wasn't found by url: ", url));
    }

    @Override
    public Page save(Page page) {
        return pageRepositories.saveAndFlush(page);
    }

    @Override
    public void delete(Page page) {
        pageRepositories.delete(page);
    }

    @Override
    public void deleteById(Long id) {
        pageRepositories.deleteById(id);
    }
}
