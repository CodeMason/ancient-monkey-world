package games.stendhal.server.entity.npc.condition;

import games.stendhal.common.parser.Sentence;
import games.stendhal.server.core.config.annotations.Dev;
import games.stendhal.server.core.config.annotations.Dev.Category;
import games.stendhal.server.entity.Entity;
import games.stendhal.server.entity.npc.ChatCondition;
import games.stendhal.server.entity.player.Player;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

/**
 * Is the specified system property set?
 */
@Dev(category=Category.ENVIRONMENT, label="System?")
public class SystemPropertyCondition implements ChatCondition {

	private final String key;
	private final String value;

	/**
	 * Creates a new SystemPropertyCondition
	 *
	 * @param key key to check
	 */
	public SystemPropertyCondition(String key) {
		this.key = key;
		this.value = null;
	}

	/**
	 * Creates a new SystemPropertyCondition
	 *
	 * @param key key to check
	 * @param value value the key has to have.
	 */
	public SystemPropertyCondition(String key, String value) {
		this.key = key;
		this.value = value;
	}

	@Override
	public boolean fire(final Player player, final Sentence sentence, final Entity entity) {
		if (value == null) {
			return System.getProperty(key) != null;
		} else {
			return value.equals(System.getProperty(key));
		}
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("system <");
		sb.append(key);
		if (value != null) {
			sb.append("=");
			sb.append(value);
		}
		sb.append(">");
		return sb.toString();
	}

	@Override
	public int hashCode() {
		return HashCodeBuilder.reflectionHashCode(this);
	}

	@Override
	public boolean equals(final Object obj) {
		return EqualsBuilder.reflectionEquals(this, obj, false,
				SystemPropertyCondition.class);
	}

}
