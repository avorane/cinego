package vue.composant.RenduSeances;

import java.io.InputStream;
import java.net.URL;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

import modele.entite.Film;
import modele.entite.Seance;
import utils.WebService;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;

public class JPanelSeance extends JPanel{

	private static final long serialVersionUID = 1L;
	
	private Seance seance;
	private WebService webService;
	private static final Integer WIDTH = 700;
	private static final Integer HEIGHT = 200;
	
	public JPanelSeance(Seance seance) {
		this.seance = seance;
		initComponents();
		webService = new WebService();
		setInfosFilm();
	}
	
	public JPanelSeance() {
		initComponents();
	}
	
	public Seance getSeance() {
		return this.seance;
	}
	
	public static Integer getHeightJPanel() {
		return HEIGHT;
	}
	
	public static Integer getWidthJPanel() {
		return WIDTH;
	}
	
	private void initComponents() {

        jLabelImageFilm = new javax.swing.JLabel();
        jLabelTitreFilm = new javax.swing.JLabel();
        jLabelSynopsisFilm = new javax.swing.JLabel();
        jLabelHoraireSeance = new javax.swing.JLabel();

        setPreferredSize(new Dimension(WIDTH, HEIGHT));
        
        jLabelImageFilm.setBackground(new java.awt.Color(220, 19, 19));
        jLabelImageFilm.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        jLabelTitreFilm.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabelTitreFilm.setText("Titre");

        jLabelSynopsisFilm.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabelSynopsisFilm.setText("Synopsis");

        jLabelHoraireSeance.setText("Horaires");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabelImageFilm, javax.swing.GroupLayout.PREFERRED_SIZE, 209, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabelHoraireSeance, javax.swing.GroupLayout.PREFERRED_SIZE, 187, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabelTitreFilm, javax.swing.GroupLayout.DEFAULT_SIZE, 62, Short.MAX_VALUE)
                        .addGap(205, 205, 205))
                    .addComponent(jLabelSynopsisFilm, javax.swing.GroupLayout.DEFAULT_SIZE, 460, Short.MAX_VALUE)))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabelImageFilm, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabelTitreFilm)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(15, 15, 15)
                                .addComponent(jLabelHoraireSeance)))
                        .addGap(18, 18, 18)
                        .addComponent(jLabelSynopsisFilm, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 63, Short.MAX_VALUE)))
                .addContainerGap())
        );
    }// </editor-fold>
	
	private void setInfosFilm() {
		InfosFilm infosFilm = webService.getInfosFilm(seance.getFilm().getIdFilmWebService());
		jLabelImageFilm.setIcon(scaleImage(infosFilm.getImage()));
		jLabelSynopsisFilm.setText(infosFilm.getSynopsis());
		jLabelTitreFilm.setText(infosFilm.getTitre());
	}
	
	public static ImageIcon scaleImage(ImageIcon source) {
	    BufferedImage img = new BufferedImage(209, 209, BufferedImage.TYPE_INT_ARGB);
	    Graphics2D g = (Graphics2D) img.getGraphics();
	    g.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
	    g.drawImage(source.getImage(), 0, 0, 209, 209, null);
	    g.dispose();
	    ImageIcon imageicon = new ImageIcon(img);
	    return imageicon;
	}


    // Variables declaration - do not modify                     
    private javax.swing.JLabel jLabelImageFilm;
    private javax.swing.JLabel jLabelTitreFilm;
    private javax.swing.JLabel jLabelSynopsisFilm;
    private javax.swing.JLabel jLabelHoraireSeance;
	
}
