/*
	Copyright © 2010, Evelina Vrabie, Cristian Vrabie, All Rights Reserved 
 
	This file is part of Thoth.

	Thoth is free software: you can redistribute it and/or modify it under
	the terms of the GNU Lesser General Public License as published by the
	Free Software Foundation, either version 3 of the License, or (at your
	option) any later version.

	Thoth is distributed in the hope that it will be useful, but WITHOUT 
	ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or
	FITNESS FOR A PARTICULAR PURPOSE.  See the GNU Lesser General Public 
	License for more details.

	You should have received a copy of the GNU Lesser General Public License
	along with Thoth.  If not, see <http://www.gnu.org/licenses/>.
	
 */
package org.codeandmagic.thoth.miscwidgets.interpolator;

/*
 *
 * Open source under the BSD License. 
 * 
 * Copyright © 2001 Robert Penner
 * All rights reserved.
 * 
 * Redistribution and use in source and binary forms, with or without modification, 
 * are permitted provided that the following conditions are met:
 * 
 * Redistributions of source code must retain the above copyright notice, this list of 
 * conditions and the following disclaimer.
 * Redistributions in binary form must reproduce the above copyright notice, this list 
 * of conditions and the following disclaimer in the documentation and/or other materials 
 * provided with the distribution.
 * 
 * Neither the name of the author nor the names of contributors may be used to endorse 
 * or promote products derived from this software without specific prior written permission.
 * 
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS" AND ANY 
 * EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES OF
 * MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE DISCLAIMED. IN NO EVENT SHALL THE
 * COPYRIGHT OWNER OR CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL,
 * EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE
 * GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED 
 * AND ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING
 * NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED 
 * OF THE POSSIBILITY OF SUCH DAMAGE. 
 *
 */

import org.codeandmagic.thoth.miscwidgets.interpolator.EasingType.Type;

import android.view.animation.Interpolator;

public class ExpoInterpolator implements Interpolator {

	private final Type type;

	public ExpoInterpolator(Type type) {
		this.type = type;
	}

	public float getInterpolation(float t) {
		if (type == Type.IN) {
			return in(t);
		}
		else if (type == Type.OUT) {
			return out(t);
		}
		else if (type == Type.INOUT) {
			return inout(t);
		}
		return 0;
	}

	private float in(float t) {
		return (float) ((t == 0) ? 0 : Math.pow(2, 10 * (t - 1)));
	}

	private float out(float t) {
		return (float) ((t >= 1) ? 1 : (-Math.pow(2, -10 * t) + 1));
	}

	private float inout(float t) {
		if (t == 0) {
			return 0;
		}
		if (t >= 1) {
			return 1;
		}
		t *= 2;
		if (t < 1) {
			return (float) (0.5f * Math.pow(2, 10 * (t - 1)));
		}
		else {
			return (float) (0.5f * (-Math.pow(2, -10 * --t) + 2));
		}
	}
}
