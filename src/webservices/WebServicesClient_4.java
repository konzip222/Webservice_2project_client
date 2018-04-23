package webservices;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Iterator;
import java.util.List;
import java.util.regex.Pattern;
import javax.imageio.ImageIO;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.ListSelectionModel;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.xml.namespace.QName;
import javax.xml.ws.Service;
import javax.xml.ws.soap.MTOMFeature;
import org.apache.pdfbox.exceptions.COSVisitorException;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.edit.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.apache.pdfbox.pdmodel.graphics.xobject.PDJpeg;
import org.apache.pdfbox.pdmodel.graphics.xobject.PDXObjectImage;
import org.jg.rsi.HelloWorld;
import org.jg.rsi.Events;
import org.jg.rsi.HelloWorldImplService;

/**
 *
 * @author Jakub
 */
//@HandlerChain(file="handler-chain.xml") // implements actionlistener
public class WebServicesClient_4 extends JFrame {

    // Pattern for checking is input a number
    final Pattern pattern = Pattern.compile("[0-9]{1,}");
    // Creating variables
    SearchField searchFiled = new SearchField();
    Events newEvent = new Events();
    int selections[];
    int id = 0;
    List responseList;
    String pdfHeader = "select Date!";

    public static void main(String[] args) throws MalformedURLException {
        new WebServicesClient_4().setVisible(true);
    }

    WebServicesClient_4() throws MalformedURLException {
        // connectiong with Webservice
        URL url = new URL("http://kuba_dom:8080/WebApplicationProject2/HelloWorldImplService?wsdl");
        QName qname = new QName("http://rsi.jg.org/", "HelloWorldImplService");
     //   Service service = Service.create(url, qname);
     //   HelloWorld webservice = service.getPort(HelloWorld.class);
        HelloWorldImplService service = new HelloWorldImplService();
        HelloWorld webservice = service.getHelloWorldImplPort(new MTOMFeature());
        byte[] image = webservice.getImageByName("event.jpg");
        /*JFrame frame = new JFrame();
        frame.setSize(300, 300);
        JLabel label = new JLabel(new ImageIcon(image));
        frame.add(label);
        frame.setVisible(true);*/
        // creating GUI
        this.initComponents();
        // initialization of text input listeners
        this.initTextInputListeners();
        // jList action Listener
        jList1.addListSelectionListener(new ListSelectionListener() {
            public void valueChanged(ListSelectionEvent listSelectionEvent) {
                System.out.println("ValueChanged"+listSelectionEvent.getFirstIndex());
                boolean adjust = listSelectionEvent.getValueIsAdjusting();
                if (!adjust) {
                    JList list = (JList) listSelectionEvent.getSource();
                    selections = list.getSelectedIndices();
                    Object selectionValues[] = list.getSelectedValues();
                    for (int i = 0, n = selections.length; i < n; i++) {
                        if (i == 0) {
                            System.out.println(" Selections: ");
                        }
                        System.out.println(selections[i] + "/" + selectionValues[i] + " ");
                    }
                } 
            }
        });
        // Buttons
        // Add Event Button
        jButton1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("event added");
                webservice.addEvent(newEvent.getName(), newEvent.getType(), newEvent.getDay(), newEvent.getMonth(), newEvent.getYear(), newEvent.getDescription());
            }
        });
        // Modify Event Button
        jButton3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("event modified");
                webservice.modifyEvent(newEvent.getId(), newEvent.getName(), newEvent.getType(), newEvent.getDay(), newEvent.getMonth(), newEvent.getYear(), newEvent.getDescription());
            }
        });
        // get Events of Day Button
        jButton4.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("getEventsOfDay");
                pdfHeader = "Events of Day: ";
                responseList = webservice.getEventsOfDay(searchFiled.getDay(), searchFiled.getMonth(), searchFiled.getYear());
                Iterator it = responseList.iterator();
                String result = "";
                DefaultListModel<String> DLM = new DefaultListModel<>();
                while (it.hasNext()) {
                    Events eve = (Events) it.next();
                    DLM.addElement(eve.getName());
                    result += eve.getName() + "\n";
                }
                jList1.setModel(DLM);
                jList1.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
                System.out.println(result);
            }
        });
        // get Events of Week Button        
        jButton5.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("getEventsOfWeek");
                pdfHeader = "Events of Week: ";
                responseList = webservice.getEventsOfWeek(searchFiled.getWeek(), searchFiled.getYearByWeek());
                Iterator it = responseList.iterator();
                String result = "";
                DefaultListModel<String> DLM = new DefaultListModel<>();
                while (it.hasNext()) {
                    Events eve = (Events) it.next();
                    DLM.addElement(eve.getName());
                    result += eve.getName() + "\n";
                }
                jList1.setModel(DLM);
                jList1.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
                System.out.println(result);
            }
        });
        // get Description button
        jButton7.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("getDescription");
                // TODO fix it
                if(selections[0] != 5){
                    Events eve = (Events) responseList.get(selections[0]);
                    String resultString = "ID: "+eve.getId()+"\n"
                            +"Name: "+eve.getName()+"\n"
                            +"Type: "+eve.getType()+"\n"
                            +"Day: "+eve.getDay()+"\n"
                            +"Month: "+eve.getMonth()+"\n"
                            +"Year: "+eve.getYear()+"\n"
                            +"Description: "+eve.getDescription();
                    JOptionPane.showMessageDialog(jEditorPane2, resultString);
                }                        
            }
        });
        // generate PDF button
        jButton6.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("PDF");
                try{
                    System.out.println("Create Simple PDF file with Text");
                    PDDocument doc = new PDDocument();
                    PDPage page = new PDPage();
                    doc.addPage( page );
                    // Creating Image
                    BufferedImage img = ImageIO.read(new ByteArrayInputStream(image));
                    BufferedImage imgResized = resize(img,55,55);
                    PDXObjectImage image2 = new PDJpeg(doc,imgResized);
                    PDPageContentStream contentStream = new PDPageContentStream(doc, page, true, true);
                    // Adding page title
                    contentStream.beginText();
                    contentStream.setFont(PDType1Font.HELVETICA, 26);
                    contentStream.moveTextPositionByAmount(160, 750);
                    if(pdfHeader.equals("Events of Day: ")){
                        contentStream.drawString(pdfHeader+searchFiled.getDay()+"/"+searchFiled.getMonth()+"/"+searchFiled.getYear());
                    }
                    else{
                        contentStream.drawString(pdfHeader+searchFiled.getWeek());
                    }
                    contentStream.endText();
                    // Creating content table
                    String[][] content = new String[(responseList.size()+1)][4];
                    content[0][0] ="ID";
                    content[0][1] ="Name";
                    content[0][2] ="Type";
                    content[0][3] ="Date";                    
                    for(int i = 1; i < (responseList.size()+1); i++){
                        Events eve = (Events) responseList.get(i-1);
                        content[i][0] = ""+i;                        
                        content[i][1] = eve.getName();
                        content[i][2] = eve.getType();
                        content[i][3] = ""+eve.getDay()+"/"+eve.getMonth()+"/"+eve.getYear();                            
                    }
                    // drawing table
                    drawTable(page, contentStream, 700, 100, content);
                    // adding image
                    contentStream.drawImage(image2, 540, 730);
                    contentStream.close();
                    // adding doument name
                    doc.save("test.pdf" );
                    // displaying path
                    System.out.println("your file created in : "+ System.getProperty("user.dir"));
                    doc.close();
                }
                catch(IOException | COSVisitorException z){
                    System.out.println(z.getMessage());
                }
            }
        });
    }
    // Method for adding Text Input Listeners
    private void initTextInputListeners() {

        // Search Fields
        // Search Day
        jTextField5.getDocument().addDocumentListener(new DocumentListener() {
            public void getTextInput() {
                // Checking if input is a number
                if (pattern.matcher(jTextField5.getText()).matches()) {
                    searchFiled.setDay(Integer.parseInt(jTextField5.getText()));
                }
            }
            @Override
            public void insertUpdate(DocumentEvent e) {
                getTextInput();
            }
            @Override
            public void removeUpdate(DocumentEvent e) {
                getTextInput();
            }
            @Override
            public void changedUpdate(DocumentEvent e) {
                getTextInput();
            }
        });
        // Search month
        jTextField6.getDocument().addDocumentListener(new DocumentListener() {
            public void getTextInput() {
                if (pattern.matcher(jTextField6.getText()).matches()) {
                    searchFiled.setMonth(Integer.parseInt(jTextField6.getText()));
                }
            }
            @Override
            public void insertUpdate(DocumentEvent e) {
                getTextInput();
            }
            @Override
            public void removeUpdate(DocumentEvent e) {
                getTextInput();
            }
            @Override
            public void changedUpdate(DocumentEvent e) {
                getTextInput();
            }
        });
        // Search Year
        jTextField9.getDocument().addDocumentListener(new DocumentListener() {
            public void getTextInput() {
                if (pattern.matcher(jTextField9.getText()).matches()) {
                    searchFiled.setYear(Integer.parseInt(jTextField9.getText()));
                }
            }
            @Override
            public void insertUpdate(DocumentEvent e) {
                getTextInput();
            }
            @Override
            public void removeUpdate(DocumentEvent e) {
                getTextInput();
            }
            @Override
            public void changedUpdate(DocumentEvent e) {
                getTextInput();
            }
        });
        // Search week
        jTextField10.getDocument().addDocumentListener(new DocumentListener() {
            public void getTextInput() {
                if (pattern.matcher(jTextField10.getText()).matches()) {
                    searchFiled.setWeek(Integer.parseInt(jTextField10.getText()));
                }
            }
            @Override
            public void insertUpdate(DocumentEvent e) {
                getTextInput();
            }
            @Override
            public void removeUpdate(DocumentEvent e) {
                getTextInput();
            }
            @Override
            public void changedUpdate(DocumentEvent e) {
                getTextInput();
            }
        });
        // Search YearByWeek
        jTextField11.getDocument().addDocumentListener(new DocumentListener() {
            public void getTextInput() {
                if (pattern.matcher(jTextField11.getText()).matches()) {
                    searchFiled.setYearByWeek(Integer.parseInt(jTextField11.getText()));
                }
            }
            @Override
            public void insertUpdate(DocumentEvent e) {
                getTextInput();
            }
            @Override
            public void removeUpdate(DocumentEvent e) {
                getTextInput();
            }
            @Override
            public void changedUpdate(DocumentEvent e) {
                getTextInput();
            }
        });
        // Events Managment
        // Event name
        jTextField3.getDocument().addDocumentListener(new DocumentListener() {
            public void getTextInput() {
                newEvent.setName(jTextField3.getText());
            }
            @Override
            public void insertUpdate(DocumentEvent e) {
                getTextInput();
            }
            @Override
            public void removeUpdate(DocumentEvent e) {
                getTextInput();
            }
            @Override
            public void changedUpdate(DocumentEvent e) {
                getTextInput();
            }
        });
        // Event type
        jTextField4.getDocument().addDocumentListener(new DocumentListener() {
            public void getTextInput() {
                newEvent.setType(jTextField4.getText());
            }
            @Override
            public void insertUpdate(DocumentEvent e) {
                getTextInput();
            }
            @Override
            public void removeUpdate(DocumentEvent e) {
                getTextInput();
            }
            @Override
            public void changedUpdate(DocumentEvent e) {
                getTextInput();
            }
        });
        // Event description
        jEditorPane2.getDocument().addDocumentListener(new DocumentListener() {
            public void getTextInput() {
                newEvent.setDescription(jEditorPane2.getText());
            }
            @Override
            public void insertUpdate(DocumentEvent e) {
                getTextInput();
            }
            @Override
            public void removeUpdate(DocumentEvent e) {
                getTextInput();
            }
            @Override
            public void changedUpdate(DocumentEvent e) {
                getTextInput();
            }
        });
        // Event day
        jTextField15.getDocument().addDocumentListener(new DocumentListener() {
            public void getTextInput() {
                if (pattern.matcher(jTextField15.getText()).matches()) {
                    newEvent.setDay(Integer.parseInt(jTextField15.getText()));
                }
            }
            @Override
            public void insertUpdate(DocumentEvent e) {
                getTextInput();
            }
            @Override
            public void removeUpdate(DocumentEvent e) {
                getTextInput();
            }
            @Override
            public void changedUpdate(DocumentEvent e) {
                getTextInput();
            }
        });
        // Event month
        jTextField16.getDocument().addDocumentListener(new DocumentListener() {
            public void getTextInput() {
                if (pattern.matcher(jTextField16.getText()).matches()) {
                    newEvent.setMonth(Integer.parseInt(jTextField16.getText()));
                }
            }
            @Override
            public void insertUpdate(DocumentEvent e) {
                getTextInput();
            }
            @Override
            public void removeUpdate(DocumentEvent e) {
                getTextInput();
            }
            @Override
            public void changedUpdate(DocumentEvent e) {
                getTextInput();
            }
        });
        // Event year
        jTextField17.getDocument().addDocumentListener(new DocumentListener() {
            public void getTextInput() {
                if (pattern.matcher(jTextField17.getText()).matches()) {
                    newEvent.setYear(Integer.parseInt(jTextField17.getText()));
                }
            }
            @Override
            public void insertUpdate(DocumentEvent e) {
                getTextInput();
            }
            @Override
            public void removeUpdate(DocumentEvent e) {
                getTextInput();
            }
            @Override
            public void changedUpdate(DocumentEvent e) {
                getTextInput();
            }
        });
        // Event ID
        jTextField18.getDocument().addDocumentListener(new DocumentListener() {
            public void getTextInput() {
                if (pattern.matcher(jTextField18.getText()).matches()) {
                    newEvent.setId(Integer.parseInt(jTextField18.getText()));
                }
            }
            @Override
            public void insertUpdate(DocumentEvent e) {
                getTextInput();
            }
            @Override
            public void removeUpdate(DocumentEvent e) {
                getTextInput();
            }
            @Override
            public void changedUpdate(DocumentEvent e) {
                getTextInput();
            }
        });
    }
    
    // Method for creating GUI
    private void initComponents() {

        // variables init
        jInternalFrame1 = new javax.swing.JInternalFrame();
        jLabel6 = new javax.swing.JLabel();
        jTextField3 = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        jTextField4 = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        jTextField15 = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jEditorPane2 = new javax.swing.JEditorPane();
        jButton1 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jTextField16 = new javax.swing.JTextField();
        jTextField17 = new javax.swing.JTextField();
        jLabel16 = new javax.swing.JLabel();
        jTextField18 = new javax.swing.JTextField();
        jInternalFrame2 = new javax.swing.JInternalFrame();
        jTextField5 = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jTextField6 = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        jTextField9 = new javax.swing.JTextField();
        jButton4 = new javax.swing.JButton();
        jLabel15 = new javax.swing.JLabel();
        jTextField11 = new javax.swing.JTextField();
        jTextField10 = new javax.swing.JTextField();
        jButton5 = new javax.swing.JButton();
        jLabel14 = new javax.swing.JLabel();
        jInternalFrame3 = new javax.swing.JInternalFrame();
        jButton6 = new javax.swing.JButton();
        jButton7 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        jList1 = new javax.swing.JList<>();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Events Managment System");
        // Internal frame 1
        jInternalFrame1.setTitle("Events Manager");
        jInternalFrame1.setName("Add Event"); // NOI18N
        jInternalFrame1.setTitle("Event Manager");
        jInternalFrame1.setVisible(true);

        jLabel6.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel6.setText("Name");

        jTextField3.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel10.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel10.setText("Type");

        jTextField4.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel11.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel11.setText("Day");

        jTextField15.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel12.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel12.setText("Month");
        jLabel13.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel13.setText("Year");
        jLabel17.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel17.setText("Description");
        jScrollPane2.setViewportView(jEditorPane2);
        jButton1.setText("addEvent");

        jButton3.setText("modifyEvent");
        jTextField16.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jTextField17.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel16.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel16.setText("ID");
        jLabel16.setToolTipText("use only with modify");
        jTextField18.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jTextField18.setToolTipText("use only with modify");

        javax.swing.GroupLayout jInternalFrame1Layout = new javax.swing.GroupLayout(jInternalFrame1.getContentPane());
        jInternalFrame1.getContentPane().setLayout(jInternalFrame1Layout);
        jInternalFrame1Layout.setHorizontalGroup(
                jInternalFrame1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jInternalFrame1Layout.createSequentialGroup()
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(jInternalFrame1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jLabel16)
                                        .addComponent(jLabel13)
                                        .addComponent(jLabel12)
                                        .addComponent(jLabel11)
                                        .addGroup(jInternalFrame1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                                .addComponent(jLabel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(jLabel6)))
                                .addGap(18, 18, 18)
                                .addGroup(jInternalFrame1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(jTextField18)
                                        .addComponent(jTextField17)
                                        .addComponent(jTextField16)
                                        .addComponent(jTextField3)
                                        .addComponent(jTextField4)
                                        .addComponent(jTextField15, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(37, 37, 37))
                        .addGroup(jInternalFrame1Layout.createSequentialGroup()
                                .addGroup(jInternalFrame1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(jInternalFrame1Layout.createSequentialGroup()
                                                .addGap(69, 69, 69)
                                                .addComponent(jLabel17))
                                        .addGroup(jInternalFrame1Layout.createSequentialGroup()
                                                .addGap(18, 18, 18)
                                                .addComponent(jButton1)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(jButton3)))
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jInternalFrame1Layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                                .addContainerGap())
        );
        jInternalFrame1Layout.setVerticalGroup(
                jInternalFrame1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jInternalFrame1Layout.createSequentialGroup()
                                .addGap(40, 40, 40)
                                .addGroup(jInternalFrame1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jLabel6)
                                        .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jInternalFrame1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jLabel10)
                                        .addComponent(jTextField4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jInternalFrame1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jLabel11)
                                        .addComponent(jTextField15, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jInternalFrame1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jLabel12)
                                        .addComponent(jTextField16, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jInternalFrame1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jLabel13)
                                        .addComponent(jTextField17, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jInternalFrame1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jLabel16)
                                        .addComponent(jTextField18, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jLabel17)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jScrollPane2)
                                .addGap(18, 18, 18)
                                .addGroup(jInternalFrame1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jButton1)
                                        .addComponent(jButton3))
                                .addGap(10, 10, 10))
        );
        jTextField3.getAccessibleContext().setAccessibleName("Name");
        // Internal frame 2
        jInternalFrame2.setTitle("Search Fields");
        jInternalFrame2.setVisible(true);
        
        jLabel7.setText("Day");
        jLabel8.setText("Month");
        jLabel9.setText("Year");
        jButton4.setLabel("getEventsOfDay");
        jButton4.setName("getEventsOfDay"); // NOI18N
        jLabel15.setText("Year");
        jButton5.setLabel("getEventsOfWeek");
        jLabel14.setText("Week");
        javax.swing.GroupLayout jInternalFrame2Layout = new javax.swing.GroupLayout(jInternalFrame2.getContentPane());
        jInternalFrame2.getContentPane().setLayout(jInternalFrame2Layout);
        jInternalFrame2Layout.setHorizontalGroup(
                jInternalFrame2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jInternalFrame2Layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(jInternalFrame2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jLabel14, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jInternalFrame2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addGroup(jInternalFrame2Layout.createSequentialGroup()
                                                .addComponent(jTextField10, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addComponent(jLabel15)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(jTextField11, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGroup(jInternalFrame2Layout.createSequentialGroup()
                                                .addComponent(jTextField5, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addComponent(jLabel8)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addComponent(jTextField6, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(18, 18, 18)
                                .addComponent(jLabel9)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jTextField9, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(jInternalFrame2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(jButton5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jButton4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addGap(26, 26, 26))
        );
        jInternalFrame2Layout.setVerticalGroup(
                jInternalFrame2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jInternalFrame2Layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(jInternalFrame2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jLabel7)
                                        .addComponent(jTextField5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jLabel8)
                                        .addComponent(jTextField6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jLabel9)
                                        .addComponent(jTextField9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jButton4))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jInternalFrame2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(jInternalFrame2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                .addComponent(jLabel14)
                                                .addComponent(jTextField10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addComponent(jButton5))
                                        .addComponent(jLabel15)
                                        .addComponent(jTextField11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addContainerGap())
        );
        // Internal frame 3
        jInternalFrame3.setTitle("Result");
        jInternalFrame3.setVisible(true);

        jButton6.setText("generate PDF");
        jButton7.setText("getDescription");

        jLabel1.setText("--------------------------------------------------------------------------------------------------------------------------------------------");
        DefaultListModel<String> DLMStart = new DefaultListModel<>();
        jList1.setModel(DLMStart);
        jScrollPane3.setViewportView(jList1);

        javax.swing.GroupLayout jInternalFrame3Layout = new javax.swing.GroupLayout(jInternalFrame3.getContentPane());
        jInternalFrame3.getContentPane().setLayout(jInternalFrame3Layout);
        jInternalFrame3Layout.setHorizontalGroup(
                jInternalFrame3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jInternalFrame3Layout.createSequentialGroup()
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jButton7)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButton6)
                                .addGap(20, 20, 20))
                        .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jInternalFrame3Layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jScrollPane3)
                                .addContainerGap())
        );
        jInternalFrame3Layout.setVerticalGroup(
                jInternalFrame3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jInternalFrame3Layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 330, Short.MAX_VALUE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jInternalFrame3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jButton6)
                                        .addComponent(jButton7))
                                .addGap(10, 10, 10))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(jInternalFrame2)
                                        .addComponent(jInternalFrame3))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jInternalFrame1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18))
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(jInternalFrame1)
                                        .addGroup(layout.createSequentialGroup()
                                                .addComponent(jInternalFrame3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addComponent(jInternalFrame2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>    

    // Variables declaration - do not modify                     
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton7;
    private javax.swing.JEditorPane jEditorPane2;
    private javax.swing.JInternalFrame jInternalFrame1;
    private javax.swing.JInternalFrame jInternalFrame2;
    private javax.swing.JInternalFrame jInternalFrame3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JList<String> jList1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTextField jTextField10;
    private javax.swing.JTextField jTextField11;
    private javax.swing.JTextField jTextField15;
    private javax.swing.JTextField jTextField16;
    private javax.swing.JTextField jTextField17;
    private javax.swing.JTextField jTextField18;
    private javax.swing.JTextField jTextField3;
    private javax.swing.JTextField jTextField4;
    private javax.swing.JTextField jTextField5;
    private javax.swing.JTextField jTextField6;
    private javax.swing.JTextField jTextField9;
    // End of variables declaration            

    // Method for drawing table
    public static void drawTable(PDPage page, PDPageContentStream contentStream,
                                float y, float margin,
                                String[][] content) throws IOException {
        final int rows = content.length;
        final int cols = content[0].length;
        final float rowHeight = 20f;
        final float tableWidth = page.findMediaBox().getWidth()-(2*margin);
        final float tableHeight = rowHeight * rows;
        final float colWidth = tableWidth/(float)cols;
        final float cellMargin=5f;

        //draw the rows
        float nexty = y ;
        for (int i = 0; i <= rows; i++) {
            contentStream.drawLine(margin,nexty,margin+tableWidth,nexty);
            nexty-= rowHeight;
        }
        //draw the columns
        float nextx = margin;
        for (int i = 0; i <= cols; i++) {
            if( i == 1){
                contentStream.drawLine((nextx-70),y,(nextx-70),y-tableHeight);
                nextx += colWidth;
                continue;
            }
            contentStream.drawLine(nextx,y,nextx,y-tableHeight);
            nextx += colWidth;
        }
        //adding text
        contentStream.setFont(PDType1Font.HELVETICA_BOLD,12);
        float textx = margin+cellMargin;
        float texty = y-15;
        for(int i = 0; i < content.length; i++){
            for(int j = 0 ; j < content[i].length; j++){
                if( j == 1){
                    String text = content[i][j];
                    contentStream.beginText();
                    contentStream.moveTextPositionByAmount((textx-70),texty);
                    contentStream.drawString(text);
                    contentStream.endText();
                    textx += colWidth;                    
                    continue;
                }
                String text = content[i][j];
                contentStream.beginText();
                contentStream.moveTextPositionByAmount(textx,texty);
                contentStream.drawString(text);
                contentStream.endText();
                textx += colWidth;
            }
            texty-=rowHeight;
            textx = margin+cellMargin;
        }
    }
    // Method for resiezing BufferedImage
    public static BufferedImage resize(BufferedImage img, int height, int width) {
        Image tmp = img.getScaledInstance(width, height, Image.SCALE_SMOOTH);
        BufferedImage resized = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2d = resized.createGraphics();
        g2d.drawImage(tmp, 0, 0, null);
        g2d.dispose();
        return resized;
    }
}
