// importando librerias
import net.proteanit.sql.DbUtils;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;


public class CreateP {
    private JPanel panel1;
    private JTextField txtName;
    private JTextField txtFrst;
    private JTextField txtLst;
    private JRadioButton optFem;
    private JRadioButton optMasc;
    private JComboBox cboBlood;
    private JComboBox cboAlerc;
    private JTextField txtAddres;
    private JTextField txtBirth;
    private JButton btnReg;
    private JComboBox cboMed;
    private JTextField txtPhone;
    private JTextField txtEmail;
    private JTextField txtPeso;
    private JTextField txtAltura;
    private JTextField txtMotivo;
    private JTable table1;
    private JButton exitButton;
    private JButton updateButton;
    private JButton deleteButton;
    private JButton searchButton;
    private JScrollPane table_1;
    private JTextField txtSearch;

    //generando palabras para
    Connection con;
        PreparedStatement pst;


        //metodo de conecxion a mysql
        public void connect(){


            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
               // con = DriverManager.getConnection("jdbc:mysql://database-1.coyoccdoaehu.us-east-1.rds.amazonaws.com:3306/Hospital","admin","123456789");
              //  con = DriverManager.getConnection("jdbc:mysql://192.168.0.125:3306/hospital","root","1234");
                con = DriverManager.getConnection("jdbc:mysql://localhost:3306/hospital","mario","1234");
                System.out.println("Successs");
            }
            catch (ClassNotFoundException ex)
            {
                ex.printStackTrace();

            }
            catch (SQLException ex)
            {
                ex.printStackTrace();
            }

    }

    public CreateP(){
            connect();
            table_load();
        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        btnReg.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String emname,frs_name,lst_name,gender,blood_type,alergic,med_alergic,addres,birth,email,peso,altura,motivo,phone;
                emname = txtName.getText();
                frs_name= txtFrst.getText();
                lst_name = txtLst.getText();
                addres = txtAddres.getText();
                birth = txtBirth.getText();
                email = txtEmail.getText();
                peso = txtPeso.getText();
                altura = txtAltura.getText();
                motivo = txtMotivo.getText();
                phone = txtPhone.getText();
                if(optFem.isSelected()){
                    gender = "Femenino";
                }else{
                    gender = "Masculino";
                }
                blood_type = String.valueOf(cboBlood.getSelectedItem());
                alergic = String.valueOf(cboAlerc.getSelectedItem());
                med_alergic = String.valueOf(cboMed.getSelectedItem());


                try{
                    pst = con.prepareStatement("INSERT INTO pacientes(emname,frs_name,lst_name,gender,blood_type,alergic,med_alergic,addres,birth,email,peso,altura,motivo,phone)" +
                            " values (?,?,?,?,?,?,?,?,?,?,?,?,?,?)" );
                        pst.setString(1,emname);
                        pst.setString(2,frs_name);
                        pst.setString(3,lst_name);
                        pst.setString(4,gender);
                        pst.setString(5,blood_type);
                        pst.setString(6,alergic);
                        pst.setString(7,med_alergic);
                        pst.setString(8,addres);
                        pst.setString(9,birth);
                        pst.setString(10,email);
                        pst.setString(11,peso);
                        pst.setString(12,altura);
                        pst.setString(13,motivo);
                        pst.setString(14,phone);
                        pst.executeUpdate();
                        JOptionPane.showMessageDialog(null,"paciente agregado");
                        table_load();
                        txtName.setText("");
                        txtAddres.setText("");
                        txtAltura.setText("");
                        txtBirth.setText("");
                        txtEmail.setText("");
                        txtFrst.setText("");
                        txtLst.setText("");
                        txtMotivo.setText("");
                        txtPeso.setText("");
                        txtPhone.setText("");
                        cboAlerc.setSelectedIndex(0);
                        cboBlood.setSelectedIndex(0);
                        cboMed.setSelectedIndex(0);
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
        });

        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            try{
                String id = txtSearch.getText();
                pst = con.prepareStatement("select emname,frs_name,lst_name,gender,blood_type,alergic,med_alergic,addres,birth,email,peso,altura,motivo,phone from pacientes where id = ?");
                pst.setString(1,id);
                ResultSet rs = pst.executeQuery();
                if(rs.next() == true){
                    String emname,frs_name,lst_name,gender,blood_type,alergic,med_alergic,addres,birth,email,peso,altura,motivo,phone;

                    emname = rs.getString(1);
                    frs_name = rs.getString(2);
                    lst_name = rs.getString(3);
                    gender = rs.getString(4);
                    blood_type = rs.getString(5);
                    alergic = rs.getString(6);
                    med_alergic = rs.getString(7);
                    addres = rs.getString(8);
                    birth = rs.getString(9);
                    email = rs.getString(10);
                    peso = rs.getString(11);
                    altura = rs.getString(12);
                    motivo = rs.getString(13);
                    phone = rs.getString(14);

                    txtName.setText(emname);
                    txtFrst.setText(frs_name);
                    txtLst.setText(lst_name);
                    if(gender.equals("Masculino")){
                        optMasc.setSelected(true);
                    }else {
                        optFem.setSelected(true);
                    }
                    txtAddres.setText(addres);
                    txtBirth.setText(birth);
                    txtEmail.setText(email);
                    txtPeso.setText(peso);
                    txtAltura.setText(altura);
                    txtMotivo.setText(motivo);
                    txtPhone.setText(phone);
                    cboBlood.setSelectedItem(blood_type);
                    cboMed.setSelectedItem(med_alergic);
                    cboAlerc.setSelectedItem(alergic);

                }else{
                    txtName.setText("");
                    txtFrst.setText("");
                    txtLst.setText("");
                    txtAddres.setText("");
                    txtPhone.setText("");
                    txtMotivo.setText("");
                    txtEmail.setText("");
                    txtAltura.setText("");
                    txtPeso.setText("");
                    txtBirth.setText("");
                    cboBlood.setSelectedIndex(0);
                    cboMed.setSelectedIndex(0);
                    cboAlerc.setSelectedIndex(0);
                    optFem.isSelected();
                    JOptionPane.showMessageDialog(null,"Id invalida");
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
            }
        });

        updateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                    String id, emname, frs_name, lst_name, gender, blood_type, alergic, med_alergic, addres, birth, email, peso, altura, motivo, phone;

                    id = txtSearch.getText();
                    emname = txtName.getText();
                    frs_name = txtFrst.getText();
                    lst_name = txtLst.getText();
                    addres = txtAddres.getText();
                    birth = txtBirth.getText();
                    email = txtEmail.getText();
                    peso = txtPeso.getText();
                    altura = txtAltura.getText();
                    motivo = txtMotivo.getText();
                    phone = txtPhone.getText();
                    if (optFem.isSelected()) {
                        gender = "Femenino";
                    } else {
                        gender = "Masculino";
                    }
                    blood_type = String.valueOf(cboBlood.getSelectedItem());
                    alergic = String.valueOf(cboAlerc.getSelectedItem());
                    med_alergic = String.valueOf(cboMed.getSelectedItem());

                    try {
                        ResultSet rs = pst.executeQuery();
                        if(rs.next()==true){
                        pst = con.prepareStatement("UPDATE pacientes set emname = ?, frs_name = ?, lst_name = ?,gender = ?,blood_type = ?,alergic = ?,med_alergic = ?, addres = ?, birth = ?,email = ?,peso = ?,altura = ?, motivo = ?, phone = ? where id = ?");

                        pst.setString(1, emname);
                        pst.setString(2, frs_name);
                        pst.setString(3, lst_name);
                        pst.setString(4, gender);
                        pst.setString(5, blood_type);
                        pst.setString(6, alergic);
                        pst.setString(7, med_alergic);
                        pst.setString(8, addres);
                        pst.setString(9, birth);
                        pst.setString(10, email);
                        pst.setString(11, peso);
                        pst.setString(12, altura);
                        pst.setString(13, motivo);
                        pst.setString(14, phone);
                        pst.setString(15, id);
                        pst.executeUpdate();
                        JOptionPane.showMessageDialog(null, "Datos actualizados");
                        table_load();
                        txtName.setText("");
                        txtFrst.setText("");
                        txtLst.setText("");
                        txtAddres.setText("");
                        txtPhone.setText("");
                        txtMotivo.setText("");
                        txtEmail.setText("");
                        txtAltura.setText("");
                        txtPeso.setText("");
                        txtBirth.setText("");
                        cboBlood.setSelectedIndex(0);
                        cboMed.setSelectedIndex(0);
                        cboAlerc.setSelectedIndex(0);
                        optFem.isSelected();
                        txtSearch.setText("");
                        txtName.requestFocus();
                        }else{
                                txtName.setText("");
                                txtFrst.setText("");
                                txtLst.setText("");
                                txtAddres.setText("");
                                txtPhone.setText("");
                                txtMotivo.setText("");
                                txtEmail.setText("");
                                txtAltura.setText("");
                                txtPeso.setText("");
                                txtBirth.setText("");
                                cboBlood.setSelectedIndex(0);
                                cboMed.setSelectedIndex(0);
                                cboAlerc.setSelectedIndex(0);
                                optFem.isSelected();
                                txtSearch.setText("");
                                txtName.requestFocus();
                                JOptionPane.showMessageDialog(null, "datos no actualizados o id invalida");
                }

                    } catch (SQLException ex) {
                        ex.printStackTrace();
                    }

            }

        });
        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String id;

                id = txtSearch.getText();
                try{
                    pst = con.prepareStatement("delete from pacientes where id = ?");

                    pst.setString(1,id);
                    pst.executeUpdate();
                    JOptionPane.showMessageDialog(null,"Paciente eliminado");
                    table_load();
                    txtName.setText("");
                    txtFrst.setText("");
                    txtLst.setText("");
                    txtAddres.setText("");
                    txtPhone.setText("");
                    txtMotivo.setText("");
                    txtEmail.setText("");
                    txtAltura.setText("");
                    txtPeso.setText("");
                    txtBirth.setText("");
                    cboBlood.setSelectedIndex(0);
                    cboMed.setSelectedIndex(0);
                    cboAlerc.setSelectedIndex(0);
                    optFem.isSelected();
                    txtSearch.setText("");
                    txtName.requestFocus();

                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
        });
    }

    void table_load(){
            try{
                pst = con.prepareStatement("select * from pacientes");
                ResultSet rs = pst.executeQuery();
                table1.setModel(DbUtils.resultSetToTableModel(rs));

            } catch (SQLException e) {
                e.printStackTrace();
            }
    }


    public static void main(String[] arg) {
        JFrame frame = new JFrame("CRUD PACIENTES");
        frame.setContentPane(new CreateP().panel1);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}