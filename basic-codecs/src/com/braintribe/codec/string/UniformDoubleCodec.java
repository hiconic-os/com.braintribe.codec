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
/**
 * 
 */
package com.braintribe.codec.string;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;

import com.braintribe.codec.Codec;
import com.braintribe.codec.CodecException;

public class UniformDoubleCodec implements Codec<Double, String> {
    private DecimalFormat decimalFormat;
    
    public UniformDoubleCodec() {
        DecimalFormatSymbols symbols = new DecimalFormatSymbols();
        symbols.setDecimalSeparator('.');
        decimalFormat = new DecimalFormat("", symbols);
        decimalFormat.setGroupingUsed(false);
        decimalFormat.setMinimumFractionDigits(1);
        decimalFormat.setMinimumIntegerDigits(1);
    }
    
    @Override
	public Double decode(String strValue) throws CodecException {
        return strValue==null || strValue.trim().length()==0 ? null : Double.parseDouble(strValue);
    }
    
    @Override
	public String encode(Double obj) throws CodecException {
        return obj==null ? "" : decimalFormat.format(obj);
    }
    
    @Override
	public Class<Double> getValueClass() {
        return Double.class;
    }
}
