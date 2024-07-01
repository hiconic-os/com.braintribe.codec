// ============================================================================
// Licensed under the Apache License, Version 2.0 (the "License");
// you may not use this file except in compliance with the License.
// You may obtain a copy of the License at
//
//     http://www.apache.org/licenses/LICENSE-2.0
//
// Unless required by applicable law or agreed to in writing, software
// distributed under the License is distributed on an "AS IS" BASIS,
// WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
// See the License for the specific language governing permissions and
// limitations under the License.
// ============================================================================
// ============================================================================
// Copyright BRAINTRIBE TECHNOLOGY GMBH, Austria, 2002-2022
// 
// This library is free software; you can redistribute it and/or modify it under the terms of the GNU Lesser General Public
// License as published by the Free Software Foundation; either version 3 of the License, or (at your option) any later version.
// 
// This library is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even the implied warranty of
// MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more details.
// 
// You should have received a copy of the GNU Lesser General Public License along with this library; See http://www.gnu.org/licenses/.
// ============================================================================
package com.braintribe.codec.string;

import java.text.Format;
import java.text.ParseException;

import org.apache.commons.lang.StringUtils;

import com.braintribe.codec.Codec;
import com.braintribe.codec.CodecException;

public class FormatCodec<T> implements Codec<T, String> {
	protected Format format;

	public FormatCodec(Format format) {
		// ugh - why isn't Format generic?!
		if (format == null)
			throw new NullPointerException("format must not be null");
		this.format = format;
	}

	@Override
	@SuppressWarnings("unchecked")
	public T decode(String s) throws CodecException {
		try {
			if (StringUtils.isBlank(s))
				return null;

			return(T) format.parseObject(s);
		} catch (ParseException e) {
			throw new CodecException(e);
		}
	}

	@Override
	public String encode(T obj) throws CodecException {
		return obj == null ? "" : format.format(obj);
	}

	@Override
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public Class<T> getValueClass() {
		return (Class) Object.class;
	}
}
