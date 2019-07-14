package in.mukuljain.moviecatalogservice.web;

import in.mukuljain.moviecatalogservice.models.CatalogItem;
import in.mukuljain.moviecatalogservice.models.Movie;
import in.mukuljain.moviecatalogservice.models.Rating;
import in.mukuljain.moviecatalogservice.models.UserRating;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

import java.lang.reflect.ParameterizedType;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/catalog")
public class MovieCatalogController {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private WebClient.Builder webClientBuilder;

    @RequestMapping("/{userId}")
    public List<CatalogItem> getCatalog(@PathVariable("userId") String userId) {

        // get all rated movie ids
        UserRating ratings = restTemplate.getForObject("http://localhost:8282/ratingsdata/users/" + userId,
                UserRating.class);

        return ratings.getUserRating().stream().map(rating -> {
            Movie movie = restTemplate.getForObject("http://localhost:8181/movies/" + rating.getMovieId(), Movie.class);

            /*Movie movie = webClientBuilder.build()
                    .get()
                    .uri("http://localhost:8181/movies/" + rating.getMovieId())
                    .retrieve()
                    .bodyToMono(Movie.class)
                    .block();*/

            return new CatalogItem(movie.getName(), "test desc", rating.getRating());
        }).collect(Collectors.toList());

        // for each movieId call movie info service and get details

        // put them all together

    }
}
