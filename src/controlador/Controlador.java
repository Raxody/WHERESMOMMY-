package controlador;

import modelo.entidad.Adopcion;
import modelo.entidad.Huerfano;
import modelo.servicio.IAdopcion;
import modelo.servicio.IHuerfano;
import modelo.servicio.ServicioAdopcion;
import modelo.servicio.ServicioHuerfano;
import vista.HuerfanosVisual;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Optional;

public class Controlador implements ActionListener {
    HuerfanosVisual huerfanosVisual = new HuerfanosVisual();
    IHuerfano servicioHuerfano;
    IAdopcion servicioAdopcion;

    public void iniciar() {
        servicioHuerfano = new ServicioHuerfano();
        servicioAdopcion = new ServicioAdopcion();
        this.huerfanosVisual.setVisible(true);
        this.huerfanosVisual.setLocationRelativeTo(null);
        this.huerfanosVisual.getBtnGuardarHuerfano().addActionListener(this);
        this.huerfanosVisual.getBtnBuscarHuerfano().addActionListener(this);
        this.huerfanosVisual.getBtnActualizarHuerfano().addActionListener(this);
        this.huerfanosVisual.getBtnLimpiarCampos().addActionListener(this);
        this.huerfanosVisual.getBtnEliminarHuerfano().addActionListener(this);

        this.huerfanosVisual.getBtnBuscarAdopcion().addActionListener(this);
        this.huerfanosVisual.getBtnSolicitarAdopcion().addActionListener(this);
        this.huerfanosVisual.getBtnAceptarAdopcion().addActionListener(this);
        this.huerfanosVisual.getBtnActualizarAdopcion().addActionListener(this);
        this.huerfanosVisual.getBtnEliminarAdopcion().addActionListener(this);
        this.huerfanosVisual.getBtnLimpiarCamposAdopcion().addActionListener(this);

        this.llenarTablaHuerfano();
        this.llenarTablaAdopcion();
    }

    public void limpiarCampos() {
        this.huerfanosVisual.getTxtEdadHuerfano().setText("");
        this.huerfanosVisual.getTxtNombreHuerfano().setText("");
        this.huerfanosVisual.getTxtIdentificacionHuerfano().setText("");
        this.huerfanosVisual.getTxtNombreAdopcion().setText("");
        this.huerfanosVisual.getTxtTelefonoAdopcion().setText("");
        this.huerfanosVisual.getCmbHuerfanos().setSelectedIndex(0);
        this.llenarTablaHuerfano();
        this.llenarTablaAdopcion();
    }

    public Controlador() {
    }

    public void llenarTablaHuerfano() {
        DefaultTableModel modelo = new DefaultTableModel();
        ArrayList<Object> nombreColumnas = new ArrayList<>();
        nombreColumnas.add("Identificacion");
        nombreColumnas.add("Nombre");
        nombreColumnas.add("Edad");
        for (Object columna : nombreColumnas) {
            modelo.addColumn(columna);
        }
        this.huerfanosVisual.getCmbHuerfanos().removeAllItems();
        this.huerfanosVisual.getCmbHuerfanos().addItem("Selecciona un huerfano");
        for (int i = 0; i < this.servicioHuerfano.listarHuerfanos().size(); i++) {
            modelo.addRow(new Object[]{this.servicioHuerfano.listarHuerfanos().get(i).getIdentificacion(),
                    this.servicioHuerfano.listarHuerfanos().get(i).getNombre(),
                    this.servicioHuerfano.listarHuerfanos().get(i).getEdad()});
            this.huerfanosVisual.getCmbHuerfanos().addItem(""+this.servicioHuerfano.listarHuerfanos().get(i).getIdentificacion());
        }
        this.huerfanosVisual.getTblHuerfanos().setModel(modelo);
    }


    public void llenarTablaAdopcion() {
        DefaultTableModel modelo = new DefaultTableModel();
        ArrayList<Object> nombreColumnas = new ArrayList<>();
        nombreColumnas.add("Identificacion huerfano");
        nombreColumnas.add("Telefono contacto");
        nombreColumnas.add("Nombre solicitante");
        for (Object columna : nombreColumnas) {
            modelo.addColumn(columna);
        }

        for (int i = 0; i < this.servicioAdopcion.listarAdopcion().size(); i++) {
            modelo.addRow(new Object[]{servicioAdopcion.listarAdopcion().get(i).getIdentificacionHuerfano(),
                    servicioAdopcion.listarAdopcion().get(i).getTelefonoContacto(),
                    servicioAdopcion.listarAdopcion().get(i).getNombreSolicitante()});
        }
        this.huerfanosVisual.getTblAdopcion().setModel(modelo);




    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (this.huerfanosVisual.getBtnGuardarHuerfano() == e.getSource()) {
            try {
                JOptionPane.showMessageDialog(null, servicioHuerfano.crearHuerfano(new Huerfano(
                        Long.parseLong(this.huerfanosVisual.getTxtIdentificacionHuerfano().getText()),
                        this.huerfanosVisual.getTxtNombreHuerfano().getText(),
                        Integer.parseInt(this.huerfanosVisual.getTxtEdadHuerfano().getText()))));
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(null, "Revisa los campos, hay errores");
            }
            limpiarCampos();
        }

        if (this.huerfanosVisual.getBtnBuscarHuerfano() == e.getSource()) {
            Optional<Huerfano> huerfano = Optional.of(new Huerfano());
            try {
                huerfano = this.servicioHuerfano.buscarHuerfano(Long.parseLong(this.huerfanosVisual.getTxtIdentificacionHuerfano().getText()));
            } catch (NumberFormatException ew) {
                JOptionPane.showMessageDialog(null, "Ingresa un valor valido en la identificación");
            }
            if (huerfano.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Huerfano no encontrado");
            } else {
                this.huerfanosVisual.getTxtNombreHuerfano().setText(huerfano.get().getNombre());
                this.huerfanosVisual.getTxtEdadHuerfano().setText("" + huerfano.get().getEdad());
            }
        }

        if (this.huerfanosVisual.getBtnActualizarHuerfano() == e.getSource()) {
            try {
                JOptionPane.showMessageDialog(null,
                        this.servicioHuerfano.actualizarHuerfano(new Huerfano(
                                Long.parseLong(this.huerfanosVisual.getTxtIdentificacionHuerfano().getText()),
                                this.huerfanosVisual.getTxtNombreHuerfano().getText(),
                                Integer.parseInt(this.huerfanosVisual.getTxtEdadHuerfano().getText()))));
                limpiarCampos();
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(null, "Revisa los campos, hay errores");
            }
        }

        if (this.huerfanosVisual.getBtnLimpiarCampos() == e.getSource()) {
            this.limpiarCampos();
        }

        if (this.huerfanosVisual.getBtnLimpiarCamposAdopcion() == e.getSource()) {
            this.limpiarCampos();
        }

        if (this.huerfanosVisual.getBtnEliminarHuerfano() == e.getSource()) {
            try {
                JOptionPane.showMessageDialog(null,
                        this.servicioHuerfano.eliminarHuerfano(Long.parseLong(this.huerfanosVisual.getTxtIdentificacionHuerfano().getText())));
                limpiarCampos();
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(null, "Revisa los campos, hay errores");
            }
        }


        if (this.huerfanosVisual.getBtnSolicitarAdopcion() == e.getSource()) {
            if (this.huerfanosVisual.getCmbHuerfanos().getSelectedIndex() != 0) {
                try {
                    JOptionPane.showMessageDialog(null, servicioAdopcion.generarSolicitudAdopcion(new Adopcion(
                            Long.parseLong(this.huerfanosVisual.getCmbHuerfanos().getSelectedItem().toString()),
                            Long.parseLong(this.huerfanosVisual.getTxtTelefonoAdopcion().getText()),
                            this.huerfanosVisual.getTxtNombreAdopcion().getText(), 2)));

                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "Revisa los campos, hay errores");
                }
            } else {
                JOptionPane.showMessageDialog(null, "Debes seleccionar un huerfano");
            }
            limpiarCampos();

        }

        if(this.huerfanosVisual.getBtnActualizarAdopcion() == e.getSource()){
            if (this.huerfanosVisual.getCmbHuerfanos().getSelectedIndex() != 0) {
                try {
                    JOptionPane.showMessageDialog(null,
                            this.servicioAdopcion.actualizarAdopcion(new Adopcion(
                                    Long.parseLong(this.huerfanosVisual.getCmbHuerfanos().getSelectedItem().toString()),
                                    Long.parseLong(this.huerfanosVisual.getTxtTelefonoAdopcion().getText()),
                                    this.huerfanosVisual.getTxtNombreAdopcion().getText(), 2)));
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "Revisa los campos, hay errores");
                }
            }else{
                JOptionPane.showMessageDialog(null, "Debes seleccionar un huerfano");
            }
            limpiarCampos();
        }


        if (this.huerfanosVisual.getBtnBuscarAdopcion() == e.getSource()) {
            if (this.huerfanosVisual.getCmbHuerfanos().getSelectedIndex() != 0) {
                Optional<Adopcion> adopcion = Optional.of(new Adopcion());

                try {
                    adopcion = this.servicioAdopcion.buscarAdopcion(Long.parseLong(this.huerfanosVisual.getCmbHuerfanos().getSelectedItem().toString()),
                            Long.parseLong(this.huerfanosVisual.getTxtTelefonoAdopcion().getText()));
                    System.out.println(adopcion.toString());
                } catch (NumberFormatException ew) {
                    JOptionPane.showMessageDialog(null, "Ingresa un valor valido en el telefono");
                }
                if (adopcion.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Huerfano no encontrado");
                } else {
                    this.huerfanosVisual.getTxtNombreAdopcion().setText(adopcion.get().getNombreSolicitante());

                }
            }else{
                JOptionPane.showMessageDialog(null, "Debes seleccionar un huerfano");
                limpiarCampos();
            }

        }

        if (this.huerfanosVisual.getBtnEliminarAdopcion() == e.getSource()) {
            if (this.huerfanosVisual.getCmbHuerfanos().getSelectedIndex() != 0) {
                try {
                     JOptionPane.showMessageDialog(null,
                             this.servicioAdopcion.eliminarAdopcion(Long.parseLong(this.huerfanosVisual.getCmbHuerfanos().getSelectedItem().toString()),
                             Long.parseLong(this.huerfanosVisual.getTxtTelefonoAdopcion().getText())));
                } catch (NumberFormatException ew) {
                    JOptionPane.showMessageDialog(null, "Ingresa un valor valido en el telefono");
                }
            }else{
                JOptionPane.showMessageDialog(null, "Debes seleccionar un huerfano");
            }
            limpiarCampos();
        }

        if (this.huerfanosVisual.getBtnAceptarAdopcion() == e.getSource()) {
            if (this.huerfanosVisual.getCmbHuerfanos().getSelectedIndex() != 0) {
                try {
                    JOptionPane.showMessageDialog(null, servicioAdopcion.aceptarSolicitudAdopcion(new Adopcion(
                            Long.parseLong(this.huerfanosVisual.getCmbHuerfanos().getSelectedItem().toString()),
                            Long.parseLong(this.huerfanosVisual.getTxtTelefonoAdopcion().getText()),
                            this.huerfanosVisual.getTxtNombreAdopcion().getText(), 1)));

                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "Revisa los campos, hay errores");
                }
            } else {
                JOptionPane.showMessageDialog(null, "Debes seleccionar un huerfano");
            }
            limpiarCampos();

        }

    }
}
