package application.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import application.model.Genero;
import application.model.GeneroRepository;
import application.model.Livro;
import application.model.LivroRepository;

@Controller
@RequestMapping("/livro")
public class LivroController {

    @Autowired
    private LivroRepository livroRepo;

    @Autowired
    private GeneroRepository generoRepo;

    @RequestMapping("/list")
    public String list(Model model) {
        model.addAttribute("livros", livroRepo.findAll());
        return "list";
    }

    @RequestMapping("/insert")
    public String insert(Model model) {
        model.addAttribute("generos", generoRepo.findAll());
        return "insert";
    }

    @RequestMapping(value = "/insert", method = RequestMethod.POST)
    public String insert(
        @RequestParam("titulo") String titulo,
        @RequestParam("isbn") String isbn,
        @RequestParam("genero") int id_genero) {
        Optional<Genero> genero = generoRepo.findById(id_genero);

        Livro livro = new Livro();
        livro.setTitulo(titulo);
        livro.setIsbn(isbn);
        livro.setGenero(genero.get());
        

        livroRepo.save(livro);

        return "redirect:/livro/list";
    }

    @RequestMapping("/update")
    public String update(Model model, @RequestParam("id") int id) {
        Optional<Livro> livro = livroRepo.findById(id);

        if(!livro.isPresent()) {
            return "redirect:/livro/list";
        }

        model.addAttribute("generos", generoRepo.findAll());
        model.addAttribute("livro", livro.get());
        return "update";
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public String update(
        @RequestParam("titulo") String titulo,
        @RequestParam("id") int id,
        @RequestParam("isbn") String isbn,
        @RequestParam("genero") int id_genero
    ) {
        Optional<Livro> livro = livroRepo.findById(id);
        if(!livro.isPresent()) {
            return "redirect:/livro/list";
        }

        livro.get().setTitulo(titulo);
        livro.get().setIsbn(isbn);
        livro.get().setGenero(generoRepo.findById(id_genero).get());

        livroRepo.save(livro.get());
        return "redirect:/livro/list";
    }

    @RequestMapping("/delete")
    public String delete(Model model, @RequestParam("id") int id) {
        Optional<Livro> livro = livroRepo.findById(id);

        if(!livro.isPresent()) {
            return "redirect:/livro/list";
        }

        model.addAttribute("livro", livro.get());
        return "delete";
    }

    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    public String delete(@RequestParam("id") int id) {
        livroRepo.deleteById(id);
        return "redirect:/livro/list";
    }
}
