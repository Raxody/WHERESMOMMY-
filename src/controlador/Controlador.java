package controlador;

import modelo.entidad.Adopcion;
import modelo.entidad.Funcionario;
import modelo.entidad.Huerfano;
import modelo.entidad.Recursos;
import modelo.servicio.*;
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
    IRecurso servicioRecurso;
    IFuncionarios servicioFuncionario;

    public void iniciar() {
        servicioHuerfano = new ServicioHuerfano();
        servicioAdopcion = new ServicioAdopcion();
        servicioRecurso = new ServicioRecurso();
        servicioFuncionario = new ServicioFuncionario();

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

        this.huerfanosVisual.getBtnActualizarFuncionario().addActionListener(this);
        this.huerfanosVisual.getBtnGuardarFuncionario().addActionListener(this);
        this.huerfanosVisual.getBtnEliminarFuncionario().addActionListener(this);
        this.huerfanosVisual.getBtnBuscarFuncionario().addActionListener(this);
        this.huerfanosVisual.getBtnLimpiarCamposFuncionario().addActionListener(this);

        this.huerfanosVisual.getBtnGuardarRecurso().addActionListener(this);
        this.huerfanosVisual.getBtnActualizarRecurso().addActionListener(this);
        this.huerfanosVisual.getBtnBuscarRecurso().addActionListener(this);
        this.huerfanosVisual.getBtnEliminarRecurso().addActionListener(this);
        this.huerfanosVisual.getBtnLimpiarRecurso().addActionListener(this);


        this.llenarTablaHuerfano();
        this.llenarTablaAdopcion();
        this.llenarTablaRecurso();
        this.llenarTablaFuncionario();
    }

    public void limpiarCampos() {
        this.huerfanosVisual.getTxtEdadHuerfano().setText("");
        this.huerfanosVisual.getTxtNombreHuerfano().setText("");
        this.huerfanosVisual.getTxtIdentificacionHuerfano().setText("");
        this.huerfanosVisual.getTxtNombreAdopcion().setText("");
        this.huerfanosVisual.getTxtTelefonoAdopcion().setText("");
        this.huerfanosVisual.getCmbHuerfanos().setSelectedIndex(0);
        this.huerfanosVisual.getTxtRecurso().setText("");
        this.huerfanosVisual.getTxtCantidad().setText("");
        this.huerfanosVisual.getTxtIdentificacionFuncionario().setText("");
        this.huerfanosVisual.getTxtNombreFuncionario().setText("");
        this.huerfanosVisual.getTxtEdadFuncionario().setText("");
        this.huerfanosVisual.getCmbFuncionario().setSelectedIndex(0);
        this.llenarTablaHuerfano();
        this.llenarTablaAdopcion();
        this.llenarTablaRecurso();
        this.llenarTablaFuncionario();
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
            this.huerfanosVisual.getCmbHuerfanos().addItem("" + this.servicioHuerfano.listarHuerfanos().get(i).getIdentificacion());
        }
        this.huerfanosVisual.getTblHuerfanos().setModel(modelo);
    }

    public void llenarTablaRecurso() {
        DefaultTableModel modelo = new DefaultTableModel();
        ArrayList<Object> nombreColumnas = new ArrayList<>();
        nombreColumnas.add("Recurso");
        nombreColumnas.add("Cantidad");
        for (Object columna : nombreColumnas) {
            modelo.addColumn(columna);
        }

        for (int i = 0; i < this.servicioRecurso.listarRecursos().size(); i++) {
            modelo.addRow(new Object[]{this.servicioRecurso.listarRecursos().get(i).getRecurso(),
                    this.servicioRecurso.listarRecursos().get(i).getCantidad()});
        }
        this.huerfanosVisual.getTblRecurso().setModel(modelo);
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

    public void llenarTablaFuncionario() {
        DefaultTableModel modelo = new DefaultTableModel();
        ArrayList<Object> nombreColumnas = new ArrayList<>();
        nombreColumnas.add("Identificacion");
        nombreColumnas.add("Nombre");
        nombreColumnas.add("Edad");
        nombreColumnas.add("Cargo");
        for (Object columna : nombreColumnas) {
            modelo.addColumn(columna);
        }

        for (int i = 0; i < this.servicioFuncionario.listarFuncionarios().size(); i++) {
            modelo.addRow(new Object[]{this.servicioFuncionario.listarFuncionarios().get(i).getIdentificacion(),
                    this.servicioFuncionario.listarFuncionarios().get(i).getNombre(),
                    this.servicioFuncionario.listarFuncionarios().get(i).getEdad(),
                    this.servicioFuncionario.listarFuncionarios().get(i).getCargo()});
        }
        this.huerfanosVisual.getTblFuncionario().setModel(modelo);
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

        if (this.huerfanosVisual.getBtnActualizarAdopcion() == e.getSource()) {
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
            } else {
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
            } else {
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
            } else {
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

        if (this.huerfanosVisual.getBtnGuardarRecurso() == e.getSource()) {
            try {
                JOptionPane.showMessageDialog(null, servicioRecurso.crearRecursos(new Recursos(
                        this.huerfanosVisual.getTxtRecurso().getText(),
                        Integer.parseInt(this.huerfanosVisual.getTxtCantidad().getText()))));
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(null, "Revisa los campos, hay errores");
            }
            limpiarCampos();
        }

        if (this.huerfanosVisual.getBtnActualizarRecurso() == e.getSource()) {
            try {
                JOptionPane.showMessageDialog(null,
                        this.servicioRecurso.actualizarRecursos(new Recursos(
                                this.huerfanosVisual.getTxtRecurso().getText(),
                                Integer.parseInt(this.huerfanosVisual.getTxtCantidad().getText()))));
                limpiarCampos();
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(null, "Revisa los campos, hay errores");
            }
        }

        if (this.huerfanosVisual.getBtnBuscarRecurso() == e.getSource()) {
            Optional<Recursos> recursos = this.servicioRecurso.buscarRecursos(this.huerfanosVisual.getTxtRecurso().getText());
            if (recursos.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Recurso no encontrado");
            } else {
                this.huerfanosVisual.getTxtCantidad().setText("" + recursos.get().getCantidad());
            }
        }

        if (this.huerfanosVisual.getBtnLimpiarRecurso() == e.getSource()) {
            this.limpiarCampos();
        }

        if (this.huerfanosVisual.getBtnEliminarRecurso() == e.getSource()) {
            JOptionPane.showMessageDialog(null,
                    this.servicioRecurso.eliminarRecursos(this.huerfanosVisual.getTxtRecurso().getText()));
            limpiarCampos();
        }


        if (this.huerfanosVisual.getBtnGuardarFuncionario() == e.getSource()) {
            if (this.huerfanosVisual.getCmbFuncionario().getSelectedIndex() == 0) {
                JOptionPane.showMessageDialog(null, "Debes seleccionar un cargo");
            } else {
                try {
                    JOptionPane.showMessageDialog(null, servicioFuncionario.crearFuncionarios(new Funcionario(
                            Long.parseLong(this.huerfanosVisual.getTxtIdentificacionFuncionario().getText()),
                            this.huerfanosVisual.getTxtNombreFuncionario().getText(),
                            this.huerfanosVisual.getCmbFuncionario().getSelectedItem().toString(),
                            Integer.parseInt(this.huerfanosVisual.getTxtEdadFuncionario().getText()))));
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "Revisa los campos, hay errores");
                }
                limpiarCampos();
            }

        }

        if (this.huerfanosVisual.getBtnActualizarFuncionario() == e.getSource()) {
            if (this.huerfanosVisual.getCmbFuncionario().getSelectedIndex() == 0) {
                JOptionPane.showMessageDialog(null, "Debes seleccionar un cargo");
            } else {
                try {
                    JOptionPane.showMessageDialog(null,
                            this.servicioFuncionario.actualizarFuncionarios(new Funcionario(
                                    Long.parseLong(this.huerfanosVisual.getTxtIdentificacionFuncionario().getText()),
                                    this.huerfanosVisual.getTxtNombreFuncionario().getText(),
                                    this.huerfanosVisual.getCmbFuncionario().getSelectedItem().toString(),
                                    Integer.parseInt(this.huerfanosVisual.getTxtEdadFuncionario().getText()))));
                    limpiarCampos();
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "Revisa los campos, hay errores");
                }
            }
        }

        if (this.huerfanosVisual.getBtnBuscarFuncionario() == e.getSource()) {

            Optional<Funcionario> funcionario = Optional.of(new Funcionario());
            try {
                funcionario = this.servicioFuncionario.buscarFuncionarios(Long.parseLong(this.huerfanosVisual.getTxtIdentificacionFuncionario().getText()));
            } catch (NumberFormatException ew) {
                JOptionPane.showMessageDialog(null, "Ingresa un valor valido en la identificación");
            }
            if (funcionario.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Funcionario no encontrado");
            } else {
                this.huerfanosVisual.getTxtNombreFuncionario().setText(funcionario.get().getNombre());
                this.huerfanosVisual.getTxtEdadFuncionario().setText("" + funcionario.get().getEdad());
                for (int i = 0; i < this.huerfanosVisual.getCmbFuncionario().getItemCount(); i++) {
                    if (this.huerfanosVisual.getCmbFuncionario().getItemAt(i).equals(funcionario.get().getCargo())) {
                        this.huerfanosVisual.getCmbFuncionario().setSelectedIndex(i);
                    }
                }


            }

        }

        if (this.huerfanosVisual.getBtnLimpiarCamposFuncionario() == e.getSource()) {
            this.limpiarCampos();

        }

        if (this.huerfanosVisual.getBtnEliminarFuncionario() == e.getSource()) {
            try {
                JOptionPane.showMessageDialog(null,
                        this.servicioFuncionario.eliminarFuncionarios(Long.parseLong(this.huerfanosVisual.getTxtIdentificacionFuncionario().getText())));
                limpiarCampos();
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(null, "Revisa los campos, hay errores");
            }
        }


    }
}
