package Library.controllers;

import Library.models.Book;
import Library.models.Person;
import Library.service.BookService;
import Library.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/books")
public class BooksController {
    private final BookService bookService;
    private final PersonService personService;

    @Autowired
    public BooksController(BookService bookService, PersonService personService) {
        this.bookService = bookService;
        this.personService = personService;
    }

    @GetMapping()
    public String index(@RequestParam(value = "page", required = false) Integer page,
                        @RequestParam(value = "books_per_page", required = false) Integer bookPerPage,
                        @RequestParam(value = "sort_by_year", required = false) boolean sortByYear,
                        Model model) {
        if (page == null || bookPerPage == null) {
            model.addAttribute("books", bookService.findAll(sortByYear));
        } else {
            model.addAttribute("books", bookService.findAllWithPagination(page, bookPerPage, sortByYear));
        }
        return "books/index";
    }

    @GetMapping("/{id}")
    public String show(@PathVariable("id") int id, Model model,
                       @ModelAttribute("person") Person person) {
        model.addAttribute("book", bookService.findById(id));

        Person owner = bookService.getBookOwner(id);
        if (owner != null) {
            model.addAttribute("owner", owner);
        } else {
            model.addAttribute("people", personService.findAll());
        }
        return "books/show";
    }

    @GetMapping("/new")
    public String newPerson(Model model) {
        model.addAttribute("book", new Book());
        return "books/new";
    }

    @PostMapping()
    public String create(@ModelAttribute("book") @Valid Book book,
                         BindingResult bindingResult) {
        if(bindingResult.hasErrors()) {
            return "books/new";
        }

        bookService.save(book);
        return "redirect:/books";
    }

    @GetMapping("/{id}/edit")
    public String edit(@PathVariable("id") int id, Model model) {
        if (bookService.findById(id).isPresent()) {
            model.addAttribute("book", bookService.findById(id).get());
        };
        return "books/edit";
    }

    @PatchMapping("/{id}")
    public String update(@PathVariable("id") int id,
                         @ModelAttribute("book") @Valid Book book,
                         BindingResult bindingResult) {
        if(bindingResult.hasErrors()) {
            return "books/edit";
        }
        bookService.update(id, book);
        return "redirect:/books";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") int id) {
        bookService.delete(id);
        return "redirect:/books";
    }

    @PatchMapping("/{id}/setBook")
    public String setBookPerson(@PathVariable("id") int id,
                                @ModelAttribute("person") Person person) {
        bookService.setBookPerson(person, id);
        return "redirect:/books/{id}";
    }
    @GetMapping("/{id}/release")
    public String releaseBook(@PathVariable("id") int id) {
        bookService.release(id);
        return "redirect:/books/{id}";
    }
    @GetMapping("/search")
    public String searchBook(Model model) {
        model.addAttribute("book", new Book());
        return "books/search";
    }
    @PostMapping("/search")
    public String findBook(@ModelAttribute("book") Book book,
                           Model model) {
        List<Book> foundBook = bookService.findByNameStartingWith(book.getName());
        model.addAttribute("foundBook", foundBook);
        return "books/search";
    }
}

