/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import domain.Administrator;
import domain.Grad;
import domain.Igrac;
import domain.Mec;
import domain.StatistikaIgraca;
import domain.Tim;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import session.Session;
import transfer.Request;
import transfer.Response;
import transfer.util.Operation;
import transfer.util.ResponseStatus;

/**
 *
 * @author PC
 */
public class ClientController {

    private static ClientController instance;

    public ClientController() {
    }

    public static ClientController getInstance() {
        if (instance == null) {
            instance = new ClientController();
        }
        return instance;
    }

    public Administrator login(Administrator administrator) throws Exception {
        return (Administrator) sendRequest(Operation.LOGIN, administrator);
    }

    public void addAdministrator(Administrator administrator) throws Exception {
        sendRequest(Operation.ADD_ADMINISTRATOR, administrator);
    }
    
    public void addIgrac(Igrac igrac) throws Exception {
        sendRequest(Operation.ADD_IGRAC, igrac);
    }
    
    public void addMec(Mec mec) throws Exception {
        sendRequest(Operation.ADD_MEC, mec);
    }
    
    public void addStatistikaIgraca(StatistikaIgraca statistikaIgraca) throws Exception {
        sendRequest(Operation.ADD_STATISTIKA_IGRACA, statistikaIgraca);
    }
    
    public void addTim(Tim tim) throws Exception {
        sendRequest(Operation.ADD_TIM, tim);
    }

    public void deleteAdministrator(Administrator administrator) throws Exception {
        sendRequest(Operation.DELETE_ADMINISTRATOR, administrator);
    }
    
    public void deleteIgrac(Igrac igrac) throws Exception {
        sendRequest(Operation.DELETE_IGRAC, igrac);
    }
    
    public void deleteTim(Tim tim) throws Exception {
        sendRequest(Operation.DELETE_TIM, tim);
    }

    public void updateAdministrator(Administrator administrator) throws Exception {
        sendRequest(Operation.UPDATE_ADMINISTRATOR, administrator);
    }
    
    public void updateTim(Tim tim) throws Exception {
        sendRequest(Operation.UPDATE_TIM, tim);
    }

    public ArrayList<Administrator> getAllAdministrator() throws Exception {
        return (ArrayList<Administrator>) sendRequest(Operation.GET_ALL_ADMINISTRATOR, null);
    }
    
    public ArrayList<Grad> getAllGrad() throws Exception {
        return (ArrayList<Grad>) sendRequest(Operation.GET_ALL_GRAD, null);
    }
    
    public ArrayList<Igrac> getAllIgrac(Tim t) throws Exception {
        return (ArrayList<Igrac>) sendRequest(Operation.GET_ALL_IGRAC, t);
    }
    
    public ArrayList<Mec> getAllMec() throws Exception {
        return (ArrayList<Mec>) sendRequest(Operation.GET_ALL_MEC, null);
    }
    
    public ArrayList<StatistikaIgraca> getAllStatistikaIgraca(Mec m) throws Exception {
        return (ArrayList<StatistikaIgraca>) sendRequest(Operation.GET_ALL_STATISTIKA_IGRACA, m);
    }
    
    public ArrayList<Tim> getAllTim() throws Exception {
        return (ArrayList<Tim>) sendRequest(Operation.GET_ALL_TIM, null);
    }
    
    private Object sendRequest(int operation, Object data) throws Exception {
        Request req = new Request(operation, data);
        ObjectOutputStream out = new ObjectOutputStream(Session.getInstance().getSocket().getOutputStream());
        out.writeObject(req);
        ObjectInputStream in = new ObjectInputStream(Session.getInstance().getSocket().getInputStream());
        Response res = (Response) in.readObject();
        if (res.getResponseStatus().equals(ResponseStatus.Error)) {
            throw res.getError();
        } else {
            return res.getData();
        }
    }
}
