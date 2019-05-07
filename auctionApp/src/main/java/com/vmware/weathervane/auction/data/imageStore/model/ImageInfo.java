/*
Copyright (c) 2017 VMware, Inc. All Rights Reserved.

Redistribution and use in source and binary forms, with or without modification, are permitted provided that the following conditions are met:
Redistributions of source code must retain the above copyright notice, this list of conditions and the following disclaimer.
Redistributions in binary form must reproduce the above copyright notice, this list of conditions and the following disclaimer in the documentation and/or other materials provided with the distribution.

THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES,
INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT HOLDER OR CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL,
SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR
SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY,
WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF
THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
*/
package com.vmware.weathervane.auction.data.imageStore.model;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;
import java.util.UUID;

import org.springframework.cassandra.core.PrimaryKeyType;
import org.springframework.data.cassandra.mapping.PrimaryKey;
import org.springframework.data.cassandra.mapping.PrimaryKeyClass;
import org.springframework.data.cassandra.mapping.PrimaryKeyColumn;
import org.springframework.data.cassandra.mapping.Table;

@Table("image_info")
public class ImageInfo implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@PrimaryKeyClass
	public static class ImageInfoKey implements Serializable {

		private static final long serialVersionUID = 1L;

		@PrimaryKeyColumn(name="entity_id", ordinal= 0, type=PrimaryKeyType.PARTITIONED)
		private Long entityid;

		@PrimaryKeyColumn(name="image_id", ordinal= 1, type=PrimaryKeyType.CLUSTERED)
		private UUID imageId;

		public Long getEntityid() {
			return entityid;
		}

		public void setEntityid(Long entityid) {
			this.entityid = entityid;
		}

		public UUID getImageId() {
			return imageId;
		}

		public void setImageId(UUID imageId) {
			this.imageId = imageId;
		}

		@Override
		public int hashCode() {
			return Objects.hash(entityid, imageId);
		}

		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			ImageInfoKey other = (ImageInfoKey) obj;
			return Objects.equals(entityid, other.entityid) 
					&& Objects.equals(imageId, other.imageId);
		}
	}
	
	@PrimaryKey
	private ImageInfoKey key;
	
	/*
	 * The field is used by the Weathervane benchmark infrastructure to 
	 * simplify cleanup between runs.
	 */
	private boolean preloaded;

	private String name;
	private String format;
	
	private Long imagenum = 0L;
	
	private Date dateadded;
	
	
	public ImageInfo() {
		
	}
	
	public ImageInfo(ImageInfo that) {
		this.key = new ImageInfoKey();
		this.key.entityid = that.key.entityid;
		this.preloaded = that.preloaded;
		this.name = that.name;
		this.format = that.format;
		this.imagenum = that.imagenum;
		this.dateadded = that.dateadded;
	}
	
	public ImageInfoKey getKey() {
		return key;
	}

	public void setKey(ImageInfoKey key) {
		this.key = key;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getFormat() {
		return format;
	}

	public void setFormat(String format) {
		this.format = format;
	}

	public Long getImagenum() {
		return imagenum;
	}

	public void setImagenum(Long imagenum) {
		this.imagenum = imagenum;
	}

	public Date getDateadded() {
		return dateadded;
	}

	public void setDateadded(Date dateadded) {
		this.dateadded = dateadded;
	}
	
	public boolean isPreloaded() {
		return preloaded;
	}

	public void setPreloaded(boolean preloaded) {
		this.preloaded = preloaded;
	}
}
