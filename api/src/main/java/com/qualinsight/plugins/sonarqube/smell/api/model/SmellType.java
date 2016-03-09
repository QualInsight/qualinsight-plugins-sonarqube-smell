/*
 * qualinsight-plugins-sonarqube-smell
 * Copyright (c) 2015, QualInsight
 * http://www.qualinsight.com/
 *
 * This program is free software: you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License as published by the Free Software Foundation, either
 * version 3 of the License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with this program. If not, you can retrieve a copy
 * from <http://www.gnu.org/licenses/>.
 */
package com.qualinsight.plugins.sonarqube.smell.api.model;

/**
 * Types of Smell that can be measured by the plugin. They have been inspired by multiple sources (see links)
 *
 * @see <a href="http://www.industriallogic.com/wp-content/uploads/2005/09/smellstorefactorings.pdf">smells to refactorings</a>
 * @see <a href="http://blog.codinghorror.com/code-smells/">code smells</a>
 *
 * @author Michel Pawlak
 */
public enum SmellType {
    /**
     * Confusing abbreviations are being used instead of explicit names.
     */
    ABBREVIATIONS_USAGE,
    /**
     * An anti-pattern has been used.
     */
    ANTI_PATTERN,
    /**
     * The design is really bad and should be improved.
     */
    BAD_DESIGN,
    /**
     * A framework is not used the way it should.
     */
    BAD_FRAMEWORK_USAGE,
    /**
     * The logging message, level, is inappropriate or the log is redundant.
     */
    BAD_LOGGING,
    /**
     * The comment or documentation text focuses on the "how" instead of the why".
     */
    HOW_COMMENT,
    /**
     * The class unnecessarily exposes its internals. Aggressively refactor classes to minimize its public surface. You should have a compelling reason for every item you make public. If you don't, hide it.
     */
    INDECENT_EXPOSURE,
    /**
     * The comment or javadoc should be reviewed: it makes no sense or does not seem to be related to the code !
     */
    MEANINGLESS_COMMENT,
    /**
     * The class delegates all its work, is it really needed ? Cut out the middle man unless you really need a wrapper.
     */
    MIDDLE_MAN,
    /**
     * A method's implementation is missing.
     */
    MISSING_IMPLEMENTATION,
    /**
     * A class or method has multiple responsibilities.
     */
    MULTIPLE_RESPONSIBILITIES,
    /**
     * Exception mechanism usage for non exceptional cases.
     */
    NON_EXCEPTION,
    /**
     * The problem is solved one way throughout the system and the same problem is solved another way in the same system. One of the solutions is the oddball or the inconsistent solution and has to be eliminated.
     */
    ODDBALL_SOLUTION,
    /**
     * There is a way to simplify this algorithm.
     */
    OVERCOMPLICATED_ALGORITHM,
    /**
     * Primitives, which include integers, Strings, doubles, arrays and other low-level language elements, are generic because many people use them. Classes, on the other hand, may be as specific as you need them to be, since you create them for specific purposes. In many cases, classes provide a simpler and more natural way to model things than primitives. In addition, once you create a class, you’ll often discover how other code in a system belongs in that class. Fowler and Beck explain how primitive obsession manifests itself when code relies too much on primitives. This typically occurs when you haven’t yet seen how a higher-level abstraction can clarify or simplify your code.
     */
    PRIMITIVES_OBSESSION,
    /**
     * The class extends another classes' methods but does not use them. Why extending the class then ?
     */
    REFUSED_BEQUEST,
    /**
     * A library does the same job, probably better.
     */
    REINVENTED_WHEEL,
    /**
     * It takes too many classes to do anything useful, you might have solution sprawl. Consider simplifying and consolidating your design.
     */
    SOLUTION_SPRAWL,
    /**
     * The code is written thinking about tomorrow's problems. Write code to solve today's problems, and worry about tomorrow's problems when they actually materialize. Everyone loses in the "what if..." school of design.
     */
    SPECULATIVE_GENERALITY,
    /**
     * The class, interface, method, field, variable or parameter name should be renamed in order to make it describe what it does or what it represents.
     */
    UNCOMMUNICATIVE_NAME,
    /**
     * The test is useless (it tests nothing.)
     */
    USELESS_TEST,
    /**
     * Wrong language (french, english, german...) is being used.
     */
    WRONG_LANGUAGE,
    /**
     * Wrong (business) logic is being used.
     */
    WRONG_LOGIC;
}
