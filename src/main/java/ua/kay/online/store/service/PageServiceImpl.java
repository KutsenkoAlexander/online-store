package ua.kay.online.store.service;

import org.springframework.stereotype.Service;
import org.testng.TestException;
import ua.kay.online.store.exception.PageNotFoundException;
import ua.kay.online.store.model.Page;
import ua.kay.online.store.repository.PageRepositories;

import java.util.List;

@Service
public class PageServiceImpl implements CrudService<Page> {

    private final PageRepositories pageRepositories;

    public PageServiceImpl(PageRepositories pageRepositories) {
        this.pageRepositories = pageRepositories;
    }

    @Override
    public List<Page> findAll() {
        return pageRepositories.findAll();
    }

    public Page findByUrl(String url) throws PageNotFoundException {
        return pageRepositories.findByUrl(url).orElseThrow(() ->  new PageNotFoundException("Page wasn't found by url: ", url));
    }

    @Override
    public Page findById(Long id) throws PageNotFoundException {
        System.out.println(">>>>>>>>>>");
        return pageRepositories.findById(id).orElseThrow(() -> new PageNotFoundException("Page wasn't found by id: ", id));
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
