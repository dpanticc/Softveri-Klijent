/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import controller.ClientController;
import domain.Igrac;
import domain.Tim;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author PC
 */
public class TableModelIgraci extends AbstractTableModel {

    private ArrayList<Igrac> lista;
    private String[] kolone = {"IgracID", "Nickname", "Ime", "Prezime"};
    int rb = 0;

    public TableModelIgraci() {
        lista = new ArrayList<>();
    }
    
    public TableModelIgraci(Tim t) {
        try {
            lista = ClientController.getInstance().getAllIgrac(t);
        } catch (Exception ex) {
            Logger.getLogger(TableModelIgraci.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public int getRowCount() {
        return lista.size();
    }

    @Override
    public int getColumnCount() {
        return kolone.length;
    }

    @Override
    public String getColumnName(int i) {
        return kolone[i];
    }

    @Override
    public Object getValueAt(int row, int column) {
        Igrac i = lista.get(row);

        switch (column) {
            case 0:
                return i.getIgracID();
            case 1:
                return i.getNickname();
            case 2:
                return i.getIme();
            case 3:
                return i.getPrezime();

            default:
                return null;
        }
    }

    public void dodajIgraca(Igrac i) {
        i.setIgracID(++rb);
        lista.add(i);
        fireTableDataChanged();
    }

    public void obrisiIgraca(int row) {
        lista.remove(row);
        
        rb = 0;
        for (Igrac igrac : lista) {
            igrac.setIgracID(++rb);
        }
        
        fireTableDataChanged();
        
    }

    public boolean postojiNick(String nick) {
        for (Igrac igrac : lista) {
            if(igrac.getNickname().equals(nick))
                return true;
        }
        return false;
    }

    public ArrayList<Igrac> getLista() {
        return lista;
    }

}
