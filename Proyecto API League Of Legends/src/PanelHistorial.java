
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.MatteBorder;
import net.rithms.riot.api.RiotApiException;
import net.rithms.riot.api.endpoints.match.dto.MatchList;

public class PanelHistorial extends JPanel {

	private JPanel listaDinamica;
	private MatchList listaPartidas;

	/**
	 * Create the frame.
	 */
	public PanelHistorial(String name) throws RiotApiException {
		setLayout(new BorderLayout());

		listaDinamica = new JPanel(new GridBagLayout());
		listaDinamica.setBackground(new Color(40, 43, 48));
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.gridwidth = GridBagConstraints.REMAINDER;
		gbc.weightx = 1;
		gbc.weighty = 1;
		JPanel constraint = new JPanel();
		constraint.setBackground(new Color(40, 43, 48));
		listaDinamica.add(constraint, gbc);

		add(new JScrollPane(listaDinamica));

		ApiRequest api = new ApiRequest();
		listaPartidas = api.requestMatchList(name);
		if (listaPartidas.getMatches().size() == 0) {
			JOptionPane.showMessageDialog(null, "No se han encontrado partidas recientes de este jugador.");
			Thread.currentThread().destroy();
		} else if (listaPartidas.getTotalGames() < 20) { // Si los registros del jugador contienen menos de 20 partidas, se muestran todas.
			// Creamos un nuevo hilo para poder ver como se van añadiendo los paneles
			// en tiempo real, sino tendríamos que esperar a que cargaran todos
			// para ver el resultado.
			new Thread(new Runnable() {
				@Override
				public void run() {
					for (int i = listaPartidas.getTotalGames(); i >= 0; i--) {
						anyadirPanel(name, i);
					}
				}
			}).start();
		} else { // Si los registros del jugador contienen más de 20 partidas, limitamos su historial a 20.
			new Thread(new Runnable() {
				@Override
				public void run() {
					for (int i = 20; i >= 0; i--) {
						anyadirPanel(name, i);
					}
				}
			}).start();
		}

	}

	public void anyadirPanel(String name, int x) {
		try {
			PanelPartida panel = new PanelPartida(name, listaPartidas, x);
			panel.setBorder(new MatteBorder(0, 0, 1, 0, new Color(40, 43, 48))); // delineamos el borde inferior del panel añadido
			GridBagConstraints gbc2 = new GridBagConstraints();
			gbc2.gridwidth = GridBagConstraints.REMAINDER;
			gbc2.weightx = 1;
			gbc2.fill = GridBagConstraints.HORIZONTAL;
			listaDinamica.add(panel, gbc2, 0);

			validate();
			repaint();
		} catch (RiotApiException e1) {
			e1.printStackTrace();
		}
	}

	@Override
	public Dimension getPreferredSize() {
		return new Dimension(730, 475);
	}
}