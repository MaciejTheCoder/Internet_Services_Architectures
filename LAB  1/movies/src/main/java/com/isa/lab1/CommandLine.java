package com.isa.lab1;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import java.util.Scanner;
import com.isa.lab1.configuration.Datainitialize;
import com.isa.lab1.director.Director;
import com.isa.lab1.director.DirectorService;
import com.isa.lab1.movie.Movie;
import com.isa.lab1.movie.MovieService;

@Component
public class CommandLine  implements CommandLineRunner{
    
    private Datainitialize newdata;
    private final MovieService movieservice;
    private DirectorService directorservice;

    @Autowired
    public CommandLine(Datainitialize newdata, MovieService movieservice, DirectorService directorservice) {
        this.newdata = newdata;
        this.movieservice = movieservice;
        this.directorservice = directorservice;
    }

    @Override
    public void run(String... args) throws Exception {

        newdata.Initialize();

        while (true){
                Scanner sc = new Scanner(System.in);
                System.out.println("0 - Exit \n1 - Add a director \n2 - Delete a director \n3 - Update a director" +
                "\n4 - List all directors\n5 - Add a movie \n6 - Delete a movie \n7 - Update a movie \n8 - List  all movies");
                switch (sc.nextInt()){
                    case 1:
                        addDirector();
                        break;
                    case 2:
                        deleteDirector();
                        break;
                    case 3:
                        updateDirector();
                    break;
                    case 4:
                        listAllDirectors();
                        break;
                    case 5:
                        addMovie();
                        break;
                    case 6:
                        deleteMovie();        
                        break;
                    case 7:
                        updateMovie();
                    break;
                    case 8:
                        listAllMovies();
                        break;
                    case 0:
                        System.exit(0);
            }
        }
    }

    public void addDirector()
    {
            Scanner sc = new Scanner(System.in);
            Director director = new Director();
            System.out.println("Enter a director name:");
            String dirname = sc.nextLine();
            if (directorservice.find(dirname).isPresent())
                System.err.println("Director already exists");
            else 
            {
                director.setname(dirname);
                System.out.println("Enter a director age:");
                director.setage(sc.nextInt());
                System.out.println("Enter a director nationality:");
                director.setnationality(sc.next());
                directorservice.create(director);
            }
    }

    public void deleteDirector()
    {
            Scanner sc = new Scanner(System.in);
            System.out.println("Enter the name of the director to be deleted:");
            String dirname = sc.nextLine();
            if (directorservice.find(dirname).isPresent())
            {
                while(true){
                    if (movieservice.finddir(directorservice.find(dirname).get()).isPresent()) {
                        movieservice.delete(directorservice.find(dirname).get());
                    }
                    else {
                        directorservice.delete(directorservice.find(dirname).get());
                        System.err.println("Any movie of this director does not exist anymore");
                        break;
                    }
                }
            }
            else
            {
                System.err.println("Director doesn't exist");
            }
    }

    public void updateDirector()
    {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the name of the director to be updated:");
        String dirname = sc.nextLine();
        if (directorservice.find(dirname).isPresent()) 
        {
            Director director = new Director();
            director.setname(dirname);
            System.out.println("Enter a director age:");
            director.setage(sc.nextInt());
            System.out.println("Enter a director nationality:");
            director.setnationality(sc.next());
            directorservice.update(director);
        }
        else 
        {
            System.err.println("Director doesn't exist");
        }
    }
    public void listAllDirectors()
    {
        directorservice.findAll().forEach(System.out::println);
    }

    public void addMovie()
    {
            Scanner sc = new Scanner(System.in);
            Movie movie = new Movie();
            System.out.println("Enter a movie director:");
            String dirname = sc.nextLine();
            if (directorservice.find(dirname).isPresent()) 
            {
                movie.setdirector(directorservice.find(dirname).get());
            }
            else
            {
                System.err.println("Director doesn't exist");
                addDirector();
                System.out.println("Enter a movie director:");
                dirname = sc.nextLine();
                if (directorservice.find(dirname).isPresent())
                {
                    movie.setdirector(directorservice.find(dirname).get());
                }
                else
                {
                    System.err.println("Director doesn't exist");
                    return;
                }
            }
            System.out.println("Enter a movie title:");
            String title = sc.nextLine();
            movie.settitle(title);
            System.out.println("Enter a movie length:");
            movie.setlength(sc.nextInt());
            movieservice.create(movie);
    }

    public void deleteMovie()
    {
            Scanner sc = new Scanner(System.in);
            System.out.println("Enter the title of the movie to be deleted:");
            String movietitle = sc.nextLine();
            if (movieservice.find(movietitle).isPresent())
            movieservice.delete(movieservice.find(movietitle).get());
            else
                System.err.println("Movie doesn't exist");
    }

    public void updateMovie()
    {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the title of the movie to be updated:");
        String moviename = sc.nextLine();
        if (movieservice.find(moviename).isPresent()) 
        {
            Movie movie = new Movie();
            movie.settitle(moviename);
            System.out.println("Enter a movie director:");
            String dirname = sc.nextLine();
            if (directorservice.find(dirname).isPresent())
            movie.setdirector(directorservice.find(dirname).get());
            else
            {
                System.err.println("Director doesn't exist");
                addDirector();
                movie.setdirector(directorservice.find(dirname).get());
            }
            System.out.println("Enter a movie length:");
            movie.setlength(sc.nextInt());
            movieservice.update(movie);
        }
        else 
        {
            System.err.println("Movie doesn't exist");
        }
    }

    public void listAllMovies()
    {
        movieservice.findAll().forEach(System.out::println);
    }

}
