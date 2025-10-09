package br.edu.ifsc.edson;

import java.util.ArrayList;

public class ControllerPlaneta {
    DAOPlaneta dao;
    public ControllerPlaneta(){
        dao = new DAOPlaneta();
        dao.add(new Planeta("Earth", R.drawable.earth));
        dao.add(new Planeta("Jupter", R.drawable.jupter));
        dao.add(new Planeta("Mars", R.drawable.mars));
        dao.add(new Planeta("Mercury", R.drawable.mercury));
        dao.add(new Planeta("Neptune", R.drawable.neptune));
        dao.add(new Planeta("saturn", R.drawable.saturn));
        dao.add(new Planeta("sun", R.drawable.sun));
        dao.add(new Planeta("uranus", R.drawable.uranus));
        dao.add(new Planeta("venus", R.drawable.venus));
    }

    public ArrayList<Planeta> getPlanetas() {
        return dao.getPlanetas();
    }

    public Planeta get(int position){
        return dao.get(position);
    }
}
