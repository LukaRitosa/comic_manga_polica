package org.example.comic_manga_polica.service.impl;

import org.example.comic_manga_polica.client.ComicVineClient;
import org.example.comic_manga_polica.client.JikanClient;
import org.example.comic_manga_polica.dto.ComicRequest;
import org.example.comic_manga_polica.entity.BookShelf;
import org.example.comic_manga_polica.entity.Comic;
import org.example.comic_manga_polica.entity.Tip;
import org.example.comic_manga_polica.exeption.NotFoundException;
import org.example.comic_manga_polica.repository.BookShelfRepository;
import org.example.comic_manga_polica.repository.ComicRepository;
import org.example.comic_manga_polica.service.ComicService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ComicServiceImpl implements ComicService {
    private final JikanClient jikanClient;
    private final ComicVineClient comicVineClient;
    private ComicRepository comicRepository;
    private BookShelfRepository bookShelfRepository;

    public ComicServiceImpl(ComicRepository comicRepository, BookShelfRepository bookShelfRepository, JikanClient jikanClient, ComicVineClient comicVineClient) {
        this.comicRepository = comicRepository;
        this.bookShelfRepository = bookShelfRepository;
        this.jikanClient = jikanClient;
        this.comicVineClient = comicVineClient;
    }

    @Override
    public List<Comic> findAll(String naziv, Integer year) {
        if (naziv != null && !naziv.isBlank() && year != null) {
            return comicRepository.findByNazivContainingIgnoreCaseAndReleaseYear(naziv, year);
        }
        if (naziv!=null && !naziv.isBlank()){
            return this.comicRepository.findByNazivContainingIgnoreCase(naziv);
        }
        if(year!=null){
            return this.comicRepository.findByReleaseYear(year);
        }
        return this.comicRepository.findAll();
    }

    @Override
    public Comic findById(Long id) {
        return this.comicRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Nema tog stripa " + id));
    }

    @Override
    public Comic create(ComicRequest req) {
        Comic comic= new Comic(null, req.getNaziv(), req.getAutor(), req.getSlika(), req.getTip(), req.getReleaseYear());

        return this.comicRepository.save(comic);
    }

    @Override
    public Comic update(Long id, ComicRequest req) {
        Comic comic= comicRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Comic not found: " + id));

        comic.setNaziv(req.getNaziv());
        comic.setAutor(req.getAutor());
        comic.setSlika(req.getSlika());
        comic.setTip(req.getTip());
        comic.setReleaseYear(req.getReleaseYear());

        return this.comicRepository.save(comic);
    }

    @Override
    public void delete(Long id) {
        if(!this.comicRepository.existsById(id)){
            throw new NotFoundException("Comic not found: " + id);
        }
        this.comicRepository.deleteById(id);

    }

    @Override
    public Comic findByShelfId(Long shelfId) {
        BookShelf bookShelf= bookShelfRepository.findById(shelfId)
                .orElseThrow(() -> new NotFoundException("Shelf not found: " + shelfId));

        return bookShelf.getComic();
    }

    @Override
    public Comic createComicFromApi(String naziv, Tip tip) {
        Comic comic= new Comic(null, naziv, "Nepoznato", "", tip, 1900);

        if (tip==Tip.MANGA){
            jikanClient.searchManga(naziv).ifPresent(data ->{
                if (data.title()!=null){
                    comic.setNaziv(data.title());
                }
                if(data.authors()!=null && !data.authors().isEmpty()){
                    comic.setAutor(data.authors().get(0).name());
                }
                if(data.images()!=null && data.images().jpg()!=null){
                    comic.setSlika(data.images().jpg().large_image_url());
                }
                if (data.published()!=null && data.published().from()!=null){
                    comic.setReleaseYear(Integer.parseInt(data.published().from().substring(0, 4)));
                }
            });
        } else {
            comicVineClient.searchComic(naziv).ifPresent(volume ->{
                if (volume.name()!=null){
                    comic.setNaziv(volume.name());
                }
                if(volume.image()!=null){
                    comic.setSlika(volume.image().medium_url());
                }
                if (volume.start_year()!=null){
                    comic.setReleaseYear(Integer.parseInt(volume.start_year()));
                }

                comicVineClient.findWriter(volume.id()).ifPresentOrElse(
                        comic::setAutor,
                        ()->comic.setAutor(volume.publisher()!=null ? volume.publisher().name() : "Nepoznato")
                );
            });
        }
        // return comicRepository.save(comic);
        return comic;
    }


}
