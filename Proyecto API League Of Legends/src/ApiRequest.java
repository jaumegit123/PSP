
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.swing.JOptionPane;

import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

import net.rithms.riot.api.ApiConfig;
import net.rithms.riot.api.RiotApi;
import net.rithms.riot.api.RiotApiException;
import net.rithms.riot.api.endpoints.match.dto.Match;
import net.rithms.riot.api.endpoints.match.dto.MatchList;
import net.rithms.riot.api.endpoints.match.dto.MatchReference;
import net.rithms.riot.api.endpoints.summoner.dto.Summoner;
import net.rithms.riot.api.request.ratelimit.RespectedRateLimitException;
import net.rithms.riot.constant.Platform;

public class ApiRequest {

	private static final String API_KEY = "RGAPI-b6ae478b-b0c6-4e77-87e4-0299bc2a60cd"; // Expira en 24 horas
	private static final ApiConfig config = new ApiConfig().setKey(API_KEY);
	private static final RiotApi api = new RiotApi(config);

	public ApiRequest() {
	}

	public Summoner requestSummoner(String name) throws RiotApiException {
		try {
			return api.getSummonerByName(Platform.EUW, name);
		} catch (RespectedRateLimitException e) {
			JOptionPane.showMessageDialog(null, e.getMessage());
			// Si hemos sobrepasado el límite de llamadas a la api, no es necesario
			// mantener el hilo actual, ya que no queremos que se pasen valores nulos
			// para crear los paneles de las partidas.
			Thread.currentThread().destroy();
		} catch(IllegalArgumentException e2) {
			JOptionPane.showMessageDialog(null, "Jugador no encontrado");
			Thread.currentThread().destroy();
		}
		return null;
	}

	public MatchList requestMatchList(String name) throws RiotApiException {
		try {
			return api.getMatchListByAccountId(Platform.EUW, requestSummoner(name).getAccountId());
		} catch (RespectedRateLimitException e) {
			JOptionPane.showMessageDialog(null, e.getMessage());
			Thread.currentThread().destroy();
		}
		return null;
	}

	public Match requestMatch(MatchList listaPartidas, String name, int x) throws RiotApiException {
		try {
			int i = 0;
			for (MatchReference match : listaPartidas.getMatches()) {
				if (i == x) {
					return api.getMatch(Platform.EUW, match.getGameId());
				}
				i++;
			}
		} catch (RespectedRateLimitException e) {
			JOptionPane.showMessageDialog(null, e.getMessage());
			Thread.currentThread().destroy();
		}
		return null;
	}

	public List<Campeon> getAllChampions() {
		List<Campeon> listaCampeones = new ArrayList<>();

		String resourceName = "res/champion.json";
		InputStream is = getClass().getResourceAsStream(resourceName);
		if (is == null) {
			throw new NullPointerException("No se puede encontrar:" + resourceName);
		}

		JSONTokener tokener = new JSONTokener(is);
		JSONObject object = new JSONObject(tokener);

		try {
			JSONObject data = object.getJSONObject("data");

			Iterator<String> keys = data.keys();
			while (keys.hasNext()) {
				String key = keys.next();
				JSONObject inner = data.getJSONObject(key);
				int id = inner.getInt("key");
				String name = inner.getString("name");
				JSONObject image = inner.getJSONObject("image");
				String champImage = image.getString("full");

				listaCampeones.add(new Campeon(id, name, champImage));
			}
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return listaCampeones;
	}

}
