package org.example;

import org.example.models.Actor;
import org.example.models.Movie;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class App {
    public static void main(String[] args) {

        Configuration configuration = new Configuration()
                .addAnnotatedClass(Actor.class)
                .addAnnotatedClass(Movie.class);

        SessionFactory sessionFactory = configuration.buildSessionFactory();

        try (sessionFactory) {
            Session session = sessionFactory.getCurrentSession();
            session.beginTransaction();

//            // Создание записей.
//            Movie movie = new Movie("Pulp fiction", 1994);
//            Actor actor1 = new Actor("Harvey Keitel", 81);
//            Actor actor2 = new Actor("Samuel L. Jackson", 72);
//            // Двусторонняя связь.
//            movie.setActors(new ArrayList<>(List.of(actor1, actor2)));
//            actor1.setMovies(new ArrayList<>(Collections.singletonList(movie)));
//            actor2.setMovies(new ArrayList<>(Collections.singletonList(movie)));
//            // Нет каскадирования, делаем всё вручную.
//            session.save(movie);
//            session.save(actor1);
//            session.save(actor2);
//
//
//            // Получение данных.
//            Movie movie0 = session.get(Movie.class, 2);
//            movie0.getActors().forEach(t -> System.out.println(t.toString()));
//
//
//            // Связывание данных.
//            Movie movie1 = new Movie("Reservoir Dogs", 1992);
//            Actor actor3 = session.get(Actor.class, 3);
//            movie1.setActors(new ArrayList<>(Collections.singletonList(actor3)));
//            // SQL тут будет автоматически сгенерированн.
//            actor3.getMovies().add(movie1);
//            session.save(movie1);


            // Удаление данных.
            Actor actor = session.get(Actor.class, 4);
            System.out.println(actor.getMovies());
            Movie movieToRemove = actor.getMovies().get(0);
            actor.getMovies().remove(movieToRemove);
            movieToRemove.getActors().remove(actor);

            session.getTransaction().commit();
        }
    }
}
