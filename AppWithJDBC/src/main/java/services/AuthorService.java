package services;

import entities.Author;
import repositories.AuthorRepository;

import java.util.List;

public class AuthorService {

    public static List<Author> getAllAuthors(){
       return AuthorRepository.getAllAuthors();
    }
}
