
import java.awt.Color;
import java.awt.Dimension;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import net.rithms.riot.api.RiotApiException;
import net.rithms.riot.api.endpoints.match.dto.Match;
import net.rithms.riot.api.endpoints.match.dto.MatchList;
import net.rithms.riot.api.endpoints.match.dto.Participant;
import net.rithms.riot.api.endpoints.match.dto.ParticipantIdentity;
import net.rithms.riot.api.endpoints.match.dto.ParticipantStats;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.Image;
import java.text.DecimalFormat;
import java.util.List;
import javax.swing.SwingConstants;

public class PanelPartida extends JPanel {

	private ApiRequest api;
	private List<Campeon> listaCampeones;
	private Match partida;
	private Participant player;
	private List<ParticipantIdentity> idJugadores;
	private List<Participant> jugadores;
	private ParticipantStats stats;

	private DecimalFormat df = new DecimalFormat("#.##");

	/**
	 * Create the frame.
	 */
	public PanelPartida(String name, MatchList listaPartidas, int x) throws RiotApiException {
		api = new ApiRequest();
		listaCampeones = api.getAllChampions();
		partida = api.requestMatch(listaPartidas, name, x);
		player = partida.getParticipantBySummonerName(name);
		idJugadores = partida.getParticipantIdentities();
		jugadores = partida.getParticipants();
		try {
			stats = player.getStats();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "No se han encontrado registros recientes de este jugador");
			Thread.currentThread().destroy();
		}
		
		setLayout(null);
		setSize(700, 120);

		JLabel lblTipoPartida = new JLabel(partida.getGameMode());
		lblTipoPartida.setFont(new Font("Segoe UI", Font.BOLD, 13));
		lblTipoPartida.setBounds(10, 11, 82, 14);
		add(lblTipoPartida);

		JLabel lblFecha = new JLabel(TimeHelper.convertDate(partida.getGameCreation()));
		lblFecha.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		lblFecha.setBounds(10, 30, 82, 14);
		add(lblFecha);

		JLabel lblResultado = new JLabel("Resultado");
		lblResultado.setFont(new Font("Segoe UI", Font.BOLD, 13));
		lblResultado.setBounds(10, 66, 82, 14);
		add(lblResultado);

		if (stats.isWin()) {
			setBackground(new Color(114, 137, 218));
			lblResultado.setText("Victoria");
			lblResultado.setForeground(new Color(0, 0, 128));
		} else {
			setBackground(new Color(229, 163, 163));
			lblResultado.setText("Derrota");
			lblResultado.setForeground(new Color(141, 2, 31));
		}

		JLabel lblDuracion = new JLabel(TimeHelper.convertDuration(partida.getGameDuration()));
		lblDuracion.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		lblDuracion.setBounds(10, 85, 82, 14);
		add(lblDuracion);

		JLabel lblCampeon = new JLabel(buscaNombreCampeon(player));
		lblCampeon.setHorizontalAlignment(SwingConstants.CENTER);
		lblCampeon.setFont(new Font("Segoe UI", Font.ITALIC, 14));
		lblCampeon.setBounds(116, 82, 96, 18);
		add(lblCampeon);

		JLabel lblScore = new JLabel(stats.getKills() + " / " + stats.getDeaths() + " / " + stats.getAssists());
		lblScore.setHorizontalAlignment(SwingConstants.CENTER);
		lblScore.setFont(new Font("Segoe UI", Font.BOLD, 15));
		lblScore.setBounds(255, 12, 97, 14);
		add(lblScore);

		JLabel lblKDA = new JLabel("Perfect KDA");
		lblKDA.setHorizontalAlignment(SwingConstants.CENTER);
		lblKDA.setFont(new Font("Segoe UI", Font.BOLD, 11));
		lblKDA.setBounds(255, 31, 97, 14);
		add(lblKDA);

		if (stats.getDeaths() != 0) {
			Double KDA = (double) stats.getKills() + stats.getAssists();
			KDA = KDA / stats.getDeaths();
			lblKDA.setText(df.format(KDA) + " KDA");
		}

		JLabel lblNivel = new JLabel("Nivel " + stats.getChampLevel());
		lblNivel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNivel.setFont(new Font("Segoe UI", Font.PLAIN, 11));
		lblNivel.setBounds(255, 66, 97, 14);
		add(lblNivel);

		Double duracion = Double.parseDouble(TimeHelper.convertDuration(partida.getGameDuration()).replace(':', '.'));

		JLabel lblFarm = new JLabel(
				stats.getTotalMinionsKilled() + "CS (" + df.format(stats.getTotalMinionsKilled() / duracion) + "/min)");
		lblFarm.setHorizontalAlignment(SwingConstants.CENTER);
		lblFarm.setFont(new Font("Segoe UI", Font.PLAIN, 11));
		lblFarm.setBounds(255, 85, 97, 14);
		add(lblFarm);

		JLabel lblJug1 = new JLabel(idJugadores.get(0).getPlayer().getSummonerName());
		lblJug1.setFont(new Font("Segoe UI", Font.PLAIN, 11));
		lblJug1.setBounds(511, 12, 75, 14);
		add(lblJug1);

		JLabel lblJug2 = new JLabel(idJugadores.get(1).getPlayer().getSummonerName());
		lblJug2.setFont(new Font("Segoe UI", Font.PLAIN, 11));
		lblJug2.setBounds(511, 31, 75, 14);
		add(lblJug2);

		JLabel lblJug3 = new JLabel(idJugadores.get(2).getPlayer().getSummonerName());
		lblJug3.setFont(new Font("Segoe UI", Font.PLAIN, 11));
		lblJug3.setBounds(511, 50, 75, 14);
		add(lblJug3);

		JLabel lblJug4 = new JLabel(idJugadores.get(3).getPlayer().getSummonerName());
		lblJug4.setFont(new Font("Segoe UI", Font.PLAIN, 11));
		lblJug4.setBounds(511, 69, 75, 14);
		add(lblJug4);

		JLabel lblJug5 = new JLabel(idJugadores.get(4).getPlayer().getSummonerName());
		lblJug5.setFont(new Font("Segoe UI", Font.PLAIN, 11));
		lblJug5.setBounds(511, 88, 75, 14);
		add(lblJug5);

		JLabel lblJug6 = new JLabel(idJugadores.get(5).getPlayer().getSummonerName());
		lblJug6.setFont(new Font("Segoe UI", Font.PLAIN, 11));
		lblJug6.setBounds(613, 12, 75, 14);
		add(lblJug6);

		JLabel lblJug7 = new JLabel(idJugadores.get(6).getPlayer().getSummonerName());
		lblJug7.setFont(new Font("Segoe UI", Font.PLAIN, 11));
		lblJug7.setBounds(613, 31, 75, 14);
		add(lblJug7);

		JLabel lblJug8 = new JLabel(idJugadores.get(7).getPlayer().getSummonerName());
		lblJug8.setFont(new Font("Segoe UI", Font.PLAIN, 11));
		lblJug8.setBounds(613, 50, 75, 14);
		add(lblJug8);

		JLabel lblJug9 = new JLabel(idJugadores.get(8).getPlayer().getSummonerName());
		lblJug9.setFont(new Font("Segoe UI", Font.PLAIN, 11));
		lblJug9.setBounds(613, 69, 75, 14);
		add(lblJug9);

		JLabel lblJug10 = new JLabel(idJugadores.get(9).getPlayer().getSummonerName());
		lblJug10.setFont(new Font("Segoe UI", Font.PLAIN, 11));
		lblJug10.setBounds(613, 88, 75, 14);
		add(lblJug10);

		JLabel img_campeon = new JLabel(
				new ImageIcon(new ImageIcon(getClass().getResource("res/img/campeones/" + buscaPNGCampeon(player)))
						.getImage().getScaledInstance(60, 60, Image.SCALE_SMOOTH)));
		img_campeon.setBounds(116, 15, 60, 60);
		add(img_campeon);

		JLabel img_sum1 = new JLabel(
				new ImageIcon(new ImageIcon(getClass().getResource("res/img/hechizos/" + player.getSpell1Id() + ".png"))
						.getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH)));
		img_sum1.setBounds(182, 15, 30, 30);
		add(img_sum1);

		JLabel img_sum2 = new JLabel(
				new ImageIcon(new ImageIcon(getClass().getResource("res/img/hechizos/" + player.getSpell2Id() + ".png"))
						.getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH)));
		img_sum2.setBounds(182, 45, 30, 30);
		add(img_sum2);

		JLabel img_maestria1 = new JLabel(new ImageIcon(new ImageIcon(getClass().getResource("res/img/runas/primary/"+ stats.getPerk0() +".png"))
				.getImage().getScaledInstance(35, 35, Image.SCALE_SMOOTH)));
		img_maestria1.setBounds(215, 15, 30, 30);
		add(img_maestria1);

		JLabel img_maestria2 = new JLabel(new ImageIcon(new ImageIcon(getClass().getResource("res/img/runas/secondary/"+ stats.getPerkSubStyle() +".png"))
				.getImage().getScaledInstance(22, 22, Image.SCALE_SMOOTH)));
		img_maestria2.setBounds(215, 45, 30, 30);
		add(img_maestria2);

		JLabel img_item1 = new JLabel(
				new ImageIcon(new ImageIcon(getClass().getResource("res/img/items/" + stats.getItem0() + ".png"))
						.getImage().getScaledInstance(25, 25, Image.SCALE_SMOOTH)));
		img_item1.setBounds(363, 30, 25, 25);
		add(img_item1);

		JLabel img_item2 = new JLabel(
				new ImageIcon(new ImageIcon(getClass().getResource("res/img/items/" + stats.getItem1() + ".png"))
						.getImage().getScaledInstance(25, 25, Image.SCALE_SMOOTH)));
		img_item2.setBounds(390, 30, 25, 25);
		add(img_item2);

		JLabel img_item3 = new JLabel(
				new ImageIcon(new ImageIcon(getClass().getResource("res/img/items/" + stats.getItem2() + ".png"))
						.getImage().getScaledInstance(25, 25, Image.SCALE_SMOOTH)));
		img_item3.setBounds(418, 30, 25, 25);
		add(img_item3);

		JLabel img_item4 = new JLabel(
				new ImageIcon(new ImageIcon(getClass().getResource("res/img/items/" + stats.getItem3() + ".png"))
						.getImage().getScaledInstance(25, 25, Image.SCALE_SMOOTH)));
		img_item4.setBounds(363, 57, 25, 25);
		add(img_item4);

		JLabel img_item5 = new JLabel(
				new ImageIcon(new ImageIcon(getClass().getResource("res/img/items/" + stats.getItem4() + ".png"))
						.getImage().getScaledInstance(25, 25, Image.SCALE_SMOOTH)));
		img_item5.setBounds(390, 57, 25, 25);
		add(img_item5);

		JLabel img_item6 = new JLabel(
				new ImageIcon(new ImageIcon(getClass().getResource("res/img/items/" + stats.getItem5() + ".png"))
						.getImage().getScaledInstance(25, 25, Image.SCALE_SMOOTH)));
		img_item6.setBounds(418, 57, 25, 25);
		add(img_item6);

		JLabel img_trinket = new JLabel(
				new ImageIcon(new ImageIcon(getClass().getResource("res/img/items/" + stats.getItem6() + ".png"))
						.getImage().getScaledInstance(25, 25, Image.SCALE_SMOOTH)));
		img_trinket.setBounds(445, 45, 25, 25);
		add(img_trinket);

		JLabel img_jug1 = new JLabel(new ImageIcon(
				new ImageIcon(getClass().getResource("res/img/campeones/" + buscaPNGCampeon(jugadores.get(0))))
						.getImage().getScaledInstance(16, 16, Image.SCALE_SMOOTH)));
		img_jug1.setBounds(492, 11, 16, 16);
		add(img_jug1);

		JLabel img_jug2 = new JLabel(new ImageIcon(
				new ImageIcon(getClass().getResource("res/img/campeones/" + buscaPNGCampeon(jugadores.get(1))))
						.getImage().getScaledInstance(16, 16, Image.SCALE_SMOOTH)));
		img_jug2.setBounds(492, 30, 16, 16);
		add(img_jug2);

		JLabel img_jug3 = new JLabel(new ImageIcon(
				new ImageIcon(getClass().getResource("res/img/campeones/" + buscaPNGCampeon(jugadores.get(2))))
						.getImage().getScaledInstance(16, 16, Image.SCALE_SMOOTH)));
		img_jug3.setBounds(492, 49, 16, 16);
		add(img_jug3);

		JLabel img_jug4 = new JLabel(new ImageIcon(
				new ImageIcon(getClass().getResource("res/img/campeones/" + buscaPNGCampeon(jugadores.get(3))))
						.getImage().getScaledInstance(16, 16, Image.SCALE_SMOOTH)));
		img_jug4.setBounds(492, 68, 16, 16);
		add(img_jug4);

		JLabel img_jug5 = new JLabel(new ImageIcon(
				new ImageIcon(getClass().getResource("res/img/campeones/" + buscaPNGCampeon(jugadores.get(4))))
						.getImage().getScaledInstance(16, 16, Image.SCALE_SMOOTH)));
		img_jug5.setBounds(492, 87, 16, 16);
		add(img_jug5);

		JLabel img_jug6 = new JLabel(new ImageIcon(
				new ImageIcon(getClass().getResource("res/img/campeones/" + buscaPNGCampeon(jugadores.get(5))))
						.getImage().getScaledInstance(16, 16, Image.SCALE_SMOOTH)));
		img_jug6.setBounds(594, 11, 16, 16);
		add(img_jug6);

		JLabel img_jug7 = new JLabel(new ImageIcon(
				new ImageIcon(getClass().getResource("res/img/campeones/" + buscaPNGCampeon(jugadores.get(6))))
						.getImage().getScaledInstance(16, 16, Image.SCALE_SMOOTH)));
		img_jug7.setBounds(594, 30, 16, 16);
		add(img_jug7);

		JLabel img_jug8 = new JLabel(new ImageIcon(
				new ImageIcon(getClass().getResource("res/img/campeones/" + buscaPNGCampeon(jugadores.get(7))))
						.getImage().getScaledInstance(16, 16, Image.SCALE_SMOOTH)));
		img_jug8.setBounds(594, 49, 16, 16);
		add(img_jug8);

		JLabel img_jug9 = new JLabel(new ImageIcon(
				new ImageIcon(getClass().getResource("res/img/campeones/" + buscaPNGCampeon(jugadores.get(8))))
						.getImage().getScaledInstance(16, 16, Image.SCALE_SMOOTH)));
		img_jug9.setBounds(594, 68, 16, 16);
		add(img_jug9);

		JLabel img_jug10 = new JLabel(new ImageIcon(
				new ImageIcon(getClass().getResource("res/img/campeones/" + buscaPNGCampeon(jugadores.get(9))))
						.getImage().getScaledInstance(16, 16, Image.SCALE_SMOOTH)));
		img_jug10.setBounds(594, 87, 16, 16);
		add(img_jug10);
	}

	public String buscaNombreCampeon(Participant p) {
		String nombre = null;
		for (int i = 0; i < listaCampeones.size(); i++) {
			if (listaCampeones.get(i).getId() == p.getChampionId()) {
				nombre = listaCampeones.get(i).getNombre();
				break;
			}
		}
		return nombre;
	}

	public String buscaPNGCampeon(Participant p) {
		String champPNG = null;
		for (int i = 0; i < listaCampeones.size(); i++) {
			if (listaCampeones.get(i).getId() == p.getChampionId()) {
				champPNG = listaCampeones.get(i).getPng();
				break;
			}
		}
		return champPNG;
	}

	@Override
	public Dimension getPreferredSize() {
		return new Dimension(700, 120);
	}

}
