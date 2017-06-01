/*
 * This file is part of BasicSql, licensed under the MIT License (MIT).
 *
 * Copyright (c) FerusTech LLC <https://ferus.tech>
 * Copyright (c) contributors
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
package tech.ferus.util.sql.sqlite;

import tech.ferus.util.sql.api.Database;
import tech.ferus.util.sql.core.BasicDatabase;

import javax.annotation.Nonnull;

/**
 * The {@link Database} implementation for SQLite.
 */
public class SqliteDatabase extends BasicDatabase {

    /**
     * The following part to: <code>jdbc:sqlite:</code>.
     */
    @Nonnull private final String path;

    /**
     * Constructs a {@link Database} for SQLite.
     */
    public SqliteDatabase() {
        this("");
    }

    /**
     * Constructs a {@link Database} for SQLite.
     *
     * @param path the data to follow <code>jdbc:sqlite:</code>
     */
    public SqliteDatabase(@Nonnull final String path) {
        super("sqlite");

        this.path = path;
    }

    /**
     * Determines whether or not the {@link Database} is to be found in memory.
     *
     * @return true if the {@link Database} is found in memory; false otherwise
     */
    public boolean isMemory() {
        return this.path.isEmpty();
    }

    /**
     * Gets the following part to: <code>jdbc:sqlite:</code>.
     *
     * @return the following part to: <code>jdbc:sqlite:</code>
     */
    @Nonnull
    public String getPath() {
        return this.path;
    }

    @Override
    public void configure() {
        if (this.isMemory()) {
            this.getDataSource().setJdbcUrl("jdbc:sqlite::memory:");
        } else {
            this.getDataSource().setJdbcUrl("jdbc:sqlite:" + this.path);
        }
    }
}