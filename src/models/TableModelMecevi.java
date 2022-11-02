/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import controller.ClientController;
import domain.Mec;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author PC
 */
public class TableModelMecevi extends AbstractTableModel implements Runnable {

    private ArrayList<Mec> lista;
    private String[] kolone = {"ID", "Crveni tim", "Plavi tim", "Dobijene runde (crveni)", 
                                "Dobijene runde (plavi)", "MVP", "Grad"};

    public TableModelMecevi() {
        try {
            lista = ClientController.getInstance().getAllMec();
        } catch (Exception ex) {
            Logger.getLogger(TableModelMecevi.class.getName()).log(Level.SEVERE, null, ex);
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
//private String[] kolone = {"ID", "Crveni tim", "Plavi tim", "Dobijene runde (crveni)", 
//                                "Dobijene runde (plavi)", "MVP", "Grad"};

    @Override
    public Object getValueAt(int row, int column) {
        Mec m = lista.get(row);

        switch (column) {
            case 0:
                return m.getMecID();
            case 1:
                return m.getCrveniTim();
            case 2:
                return m.getPlaviTim();
            case 3:
                return m.getDobijeneRundeCrveni();
            case 4: 
                return m.getDobijeneRundePlavi();
            case 5:
                return m.getMVP();
            case 6:
                return m.getGrad();

            default:
                return null;
        }
    }

    public Mec getSelectedMec(int row) {
        return lista.get(row);
    }

    @Override
    public void run() {
        try {
            while (!Thread.currentThread().isInterrupted()) {
                Thread.sleep(5000);
                refreshTable();
            }
        } catch (InterruptedException ex) {
            Logger.getLogger(TableModelMecevi.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void refreshTable() {
        try {
            lista = ClientController.getInstance().getAllMec();
            fireTableDataChanged();

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

}
