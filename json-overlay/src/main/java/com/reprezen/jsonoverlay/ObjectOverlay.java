/*******************************************************************************
 *  Copyright (c) 2017 ModelSolv, Inc. and others.
 *  All rights reserved. This program and the accompanying materials
 *  are made available under the terms of the Eclipse Public License v1.0
 *  which accompanies this distribution, and is available at
 *  http://www.eclipse.org/legal/epl-v10.html
 *
 *  Contributors:
 *     ModelSolv, Inc. - initial API and implementation and/or initial documentation
 *******************************************************************************/
package com.reprezen.jsonoverlay;

import com.fasterxml.jackson.databind.JsonNode;

public final class ObjectOverlay extends ScalarOverlay<Object> {

	private ObjectOverlay(JsonNode json, JsonOverlay<?> parent, ReferenceManager refMgr) {
		super(json, parent, factory, refMgr);
	}

	private ObjectOverlay(Object value, JsonOverlay<?> parent, ReferenceManager refMgr) {
		super(value, parent, factory, refMgr);
	}

	@Override
	protected Object _fromJson(JsonNode json) {
		return json.isMissingNode() ? null : mapper.convertValue(json, Object.class);
	}

	@Override
	protected JsonNode _toJsonInternal(SerializationOptions options) {
		return value != null ? mapper.convertValue(value, JsonNode.class) : _jsonMissing();
	}

	@Override
	protected OverlayFactory<Object> _getFactory() {
		return factory;
	}

	public static OverlayFactory<Object> factory = new OverlayFactory<Object>() {
		@Override
		protected Class<ObjectOverlay> getOverlayClass() {
			return ObjectOverlay.class;
		}

		@Override
		public ObjectOverlay _create(Object value, JsonOverlay<?> parent, ReferenceManager refMgr) {
			return new ObjectOverlay(value, parent, refMgr);
		}

		@Override
		public ObjectOverlay _create(JsonNode json, JsonOverlay<?> parent, ReferenceManager refMgr) {
			return new ObjectOverlay(json, parent, refMgr);
		}
	};
}
