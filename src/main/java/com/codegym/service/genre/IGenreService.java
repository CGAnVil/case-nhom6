package com.codegym.service.genre;

import com.codegym.model.Genre;
import com.codegym.service.IGeneralService;

public interface IGenreService extends IGeneralService<Genre>{
    Iterable<Genre> findGenreByNameContaining(String name);
}
