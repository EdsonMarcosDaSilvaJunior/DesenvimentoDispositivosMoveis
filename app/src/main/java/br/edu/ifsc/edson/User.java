package br.edu.ifsc.edson;

public class User {
    int id;
    String nome;



    public User(int id, String nome) {
        this.id = id;
        this.nome = nome;
    }

    public int getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public String toString(){
        return nome;
    }
}
