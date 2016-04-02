package spittr.data;

import spittr.Spitter;

/** @author Yuriy */
public interface SpitterRepository {
    Spitter save(Spitter spitter);
    Spitter findByUsername(String username);
}
