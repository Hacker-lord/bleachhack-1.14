/*
 * This file is part of the BleachHack distribution (https://github.com/BleachDrinker420/bleachhack-1.14/).
 * Copyright (c) 2019 Bleach.
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package bleach.hack.gui.clickgui;

import com.google.gson.JsonElement;
import com.google.gson.JsonPrimitive;

import bleach.hack.gui.clickgui.modulewindow.ModuleWindow;
import net.minecraft.client.MinecraftClient;
import net.minecraft.util.math.MathHelper;

public class SettingMode extends SettingBase {

	public String[] modes;
	public int mode;
	public String text;

	public SettingMode(String text, String... modes) {
		this.modes = modes;
		this.text = text;
	}

	public int getNextMode() {
		if (mode + 1 >= modes.length) {
			return 0;
		}

		return mode + 1;
	}
	
	public String getName() {
		return text;
	}
	
	public void render(ModuleWindow window, int x, int y, int len, int mouseX, int mouseY, boolean lmDown, boolean rmDown, boolean lmHeld) {
		window.fillGreySides(x, y - 1, x + len - 1, y + 12);
		MinecraftClient.getInstance().textRenderer.drawWithShadow(text + modes[mode],x+2, y+2,
				window.mouseOver(x, y, x + len, y + 12) ? 0xcfc3cf : 0xcfe0cf);

		if (window.mouseOver(x, y, x + len, y + 12) && lmDown) mode = getNextMode();
	}
	
	public int getHeight(int len) {
		return 12;
	}
	
	public void readSettings(JsonElement settings) {
		if (settings.isJsonPrimitive()) {
			mode = MathHelper.clamp(settings.getAsInt(), 0, modes.length);
		}
	}

	public JsonElement saveSettings() {
		return new JsonPrimitive(MathHelper.clamp(mode, 0, modes.length));
	}
}
