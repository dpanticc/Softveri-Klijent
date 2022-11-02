/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import controller.ClientController;
import domain.Tim;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author PC
 */
public class TableModelTimovi extends AbstractTableModel implements Runnable {

    private ArrayList<Tim> lista;
    private String[] kolone = {"ID", "Naziv tima", "Trener", "Broj pobeda", "Broj gubitaka", "Grad"};
    private String parametar = "";

    public TableModelTimovi() {
        try {
            lista = ClientController.getInstance().getAllTim();
        } catch (Exception ex) {
            Logger.getLogger(TableModelTimovi.class.getName()).log(Level.SEVERE, null, ex);
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
    //private String[] kolone = {"ID", "Naziv tima", "Trener", "Broj pobeda", "Broj gubitaka", "Grad"};

    @Override
    public Object getValueAt(int row, int column) {
        Tim t = lista.get(row);

        switch (column) {
            case 0:
                return t.getTimID();
            case 1:
                return t.getNazivTima();
            case 2:
                return t.getTrener();
            case 3:
                return t.getBrojPobeda();
            case 4:
                return t.getBrojGubitaka();
            case 5:
                return t.getGrad();

            default:
                return null;
        }
    }

    public Tim getSelectedTim(int row) {
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
            Logger.getLogger(TableModelTimovi.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void setParametar(String parametar) {
        this.parametar = parametar;
        refreshTable();
    }

    public void refreshTable() {
        try {
            lista = ClientController.getInstance().getAllTim();
            if (!parametar.equals("")) {
                ArrayList<Tim> novaLista = new ArrayList<>();
                for (Tim t : lista) {
                    if (t.getNazivTima().toLowerCase().contains(parametar.toLowerCase())) {
                        novaLista.add(t);
                    }
                }
                lista = novaLista;
            }

            fireTableDataChanged();

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

}
