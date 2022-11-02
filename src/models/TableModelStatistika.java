/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import controller.ClientController;
import domain.Igrac;
import domain.Mec;
import domain.StatistikaIgraca;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author PC
 */
public class TableModelStatistika extends AbstractTableModel {
    
    private ArrayList<StatistikaIgraca> lista;
    private String[] kolone = {"RB", "Igrac", "Broj ubistva", "Broj smrti", "MVP", "Tim"};
    int rb = 0;
    
    public TableModelStatistika() {
        lista = new ArrayList<>();
    }
    
    public TableModelStatistika(Mec m) {
        try {
            lista = ClientController.getInstance().getAllStatistikaIgraca(m);
        } catch (Exception ex) {
            Logger.getLogger(TableModelStatistika.class.getName()).log(Level.SEVERE, null, ex);
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
    public Class<?> getColumnClass(int i) {
        if (i == 4) {
            return Boolean.class;
        }
        return super.getColumnClass(i); //To change body of generated methods, choose Tools | Templates.
    }
    
    @Override
    public Object getValueAt(int row, int column) {
        StatistikaIgraca si = lista.get(row);
        
        switch (column) {
            case 0:
                return si.getRbStatistike();
            case 1:
                return si.getIgrac().getNickname();
            case 2:
                return si.getBrojUbistva();
            case 3:
                return si.getBrojSmrti();
            case 4:
                return si.isMVP();
            case 5:
                return si.getTim();
            
            default:
                return null;
        }
    }
    
    public void dodajStatistiku(StatistikaIgraca si) {
        si.setRbStatistike(++rb);
        lista.add(si);
        fireTableDataChanged();
    }
    
    public void obrisiStatistiku(int row) {
        lista.remove(row);
        
        rb = 0;
        for (StatistikaIgraca statistikaIgraca : lista) {
            statistikaIgraca.setRbStatistike(++rb);
        }
        
        fireTableDataChanged();
        
    }
    
    public boolean postojiIgrac(Igrac i) {
        for (StatistikaIgraca si : lista) {
            if (si.getIgrac().getNickname().equals(i.getNickname())) {
                return true;
            }
        }
        return false;
    }
    
    public ArrayList<StatistikaIgraca> getLista() {
        return lista;
    }
    
    public boolean postojiMVP() {
        for (StatistikaIgraca si : lista) {
            if (si.isMVP()) {
                return true;
            }
        }
        return false;
    }

    public void clear() {
        lista = new ArrayList<>();
        fireTableDataChanged();
    }
    
}
