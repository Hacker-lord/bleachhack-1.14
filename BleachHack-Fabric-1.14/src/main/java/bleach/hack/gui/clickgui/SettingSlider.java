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

import java.math.BigDecimal;
import java.math.RoundingMode;

import com.google.gson.JsonElement;
import com.google.gson.JsonPrimitive;

import bleach.hack.gui.clickgui.modulewindow.ModuleWindow;
import net.minecraft.client.MinecraftClient;
import net.minecraft.util.math.MathHelper;

public class SettingSlider extends SettingBase {

	public double min;
	public double max;
	private double value;
	public int round;
	public String text;

	public SettingSlider(String text, double min, double max, double value, int round) {
		this.min = min;
		this.max = max;
		this.value = value;
		this.round = round;
		this.text = text;
	}

	public double getValue() {
		return round(value, round);
	}

	public void setValue(double value) {
		this.value = value;
	}

	public double round(double value, int places) {
		BigDecimal bd = new BigDecimal(value);
		bd = bd.setScale(places, RoundingMode.HALF_UP);
		return bd.doubleValue();
	}
	
	public String getName() {
		return text;
	}
	
	public void render(ModuleWindow window, int x, int y, int len, int mouseX, int mouseY, boolean lmDown, boolean rmDown, boolean lmHeld) {
		int pixels = (int) Math.round(MathHelper.clamp((len-2)*((getValue() - min) / (max - min)), 0, len-2));
		window.fillGreySides(x, y-1, x+len-1, y+12);
		window.fillGradient(x+1, y, x+pixels, y+12, 0xf03080a0, 0xf02070b0);

		MinecraftClient.getInstance().textRenderer.drawWithShadow(text + (round == 0  && getValue() > 100 ? Integer.toString((int)getValue()) : getValue()),
				x+2, y+2, window.mouseOver(x, y, x+len, y+12) ? 0xcfc3cf : 0xcfe0cf);

		if (window.mouseOver(x+1, y, x+len-2, y+12) && lmHeld) {
			int percent = ((mouseX - x) * 100) / (len - 2);

			setValue(round(percent*((max - min) / 100) + min, round));
		}
	}
	
	public int getHeight(int len) {
		return 12;
	}
	
	public void readSettings(JsonElement settings) {
		if (settings.isJsonPrimitive()) {
			setValue(settings.getAsDouble());
		}
	}

	public JsonElement saveSettings() {
		return new JsonPrimitive(getValue());
	}
}
