/*!
 * jQuery QueryBuilder 1.3.0
 * Copyright 2014 Damien "Mistic" Sorel (http://www.strangeplanet.fr)
 * Licensed under MIT (http://opensource.org/licenses/MIT)
 */
!function (a) {
    "use strict";
    function b() {
        function a(b, c) {
            var d, e;
            "object" != typeof b && (b = {});
            for (e in c)
                c.hasOwnProperty(e) && (d = c[e], b[e] = d && "renderTo" !== e && "number" != typeof d.nodeType && "object" == typeof d && "[object Array]" !== Object.prototype.toString.call(d) ? a(b[e] || {}, d) : c[e]);
            return b
        }
        var b, c, d = arguments, e = {};
        for (d[0] === !0 && (e = d[1], d = Array.prototype.slice.call(d, 2)), c = d.length, b = 0; c > b; b++)
            e = a(e, d[b]);
        return e
    }
    function c(b, c) {
        b && (a.isArray(b) ? a.each(b, function (b, d) {
            a.isPlainObject(d) ? a.each(d, function (a, b) {
                return c(a, b), !1
            }) : c(b, d)
        }) : a.each(b, function (a, b) {
            c(a, b)
        }))
    }
    var d = ["string", "integer", "double", "date", "time", "datetime"], e = ["text", "radio", "checkbox", "select"], f = function (c, d) {
        this.$el = c, this.settings = b(f.DEFAULTS, d), this.status = {group_id: 0, rule_id: 0, generatedId: !1, has_optgroup: !1}, this.filters = this.settings.filters, this.lang = this.settings.lang, this.operators = this.settings.operators, this.template = this.settings.template, null === this.template.group && (this.template.group = this.getGroupTemplate), null === this.template.rule && (this.template.rule = this.getRuleTemplate), this.$el.attr("id") || (this.$el.attr("id", "qb_" + Math.floor(99999 * Math.random())), this.status.generatedId = !0), this.$el_id = this.$el.attr("id"), (!this.filters || this.filters.length < 1) && a.error("Missing filters list"), this.checkFilters(), this.init(d)
    };
    f.DEFAULTS = {onValidationError: null, onAfterAddGroup: null, onAfterAddRule: null, allow_groups: !0, sortable: !1, filters: [], conditions: ["AND", "OR"], default_condition: "AND", readonly_behavior: {delete_group: !1, sortable: !0}, template: {group: null, rule: null}, lang: {add_rule: "ADD RULE", add_group: "ADD GROUP", delete_rule: "DELETE", delete_group: "DELETE", condition_and: "AND", condition_or: "OR", filter_select_placeholder: "------------------------------", operator_equal: "EQUAL", operator_not_equal: "NOT EQUAL", operator_in: "IS IN", operator_not_in: "IS NOT IN", operator_less: "LESS", operator_less_or_equal: "LESS OR EQUAL", operator_greater: "GREATER", operator_greater_or_equal: "GREATER OR EQUAL", operator_between: "BETWEEN", operator_begins_with: "BEGINS WITH", operator_not_begins_with: "DOSESN'T BEGIN WITH", operator_contains: "CONTAINS", operator_not_contains: "DOESN'T CONTAIN", operator_ends_with: "ENDS WITH", operator_not_ends_with: "DOESN'T END WITH", operator_is_empty: "IS EMPTY", operator_is_not_empty: "IS NOT EMPTY", operator_is_null: "IS NULL", operator_is_not_null: "IS NOT NULL"}, operators: [{type: "equal", accept_values: 1, apply_to: ["string", "number", "datetime"]}, {type: "not_equal", accept_values: 1, apply_to: ["string", "number", "datetime"]}, {type: "in", accept_values: 1, apply_to: ["string", "number", "datetime"]}, {type: "not_in", accept_values: 1, apply_to: ["string", "number", "datetime"]}, {type: "less", accept_values: 1, apply_to: ["number", "datetime"]}, {type: "less_or_equal", accept_values: 1, apply_to: ["number", "datetime"]}, {type: "greater", accept_values: 1, apply_to: ["number", "datetime"]}, {type: "greater_or_equal", accept_values: 1, apply_to: ["number", "datetime"]}, {type: "between", accept_values: 2, apply_to: ["number", "datetime"]}, {type: "begins_with", accept_values: 1, apply_to: ["string"]}, {type: "not_begins_with", accept_values: 1, apply_to: ["string"]}, {type: "contains", accept_values: 1, apply_to: ["string"]}, {type: "not_contains", accept_values: 1, apply_to: ["string"]}, {type: "ends_with", accept_values: 1, apply_to: ["string"]}, {type: "not_ends_with", accept_values: 1, apply_to: ["string"]}, {type: "is_empty", accept_values: 0, apply_to: ["string"]}, {type: "is_not_empty", accept_values: 0, apply_to: ["string"]}, {type: "is_null", accept_values: 0, apply_to: ["string", "number", "datetime"]}, {type: "is_not_null", accept_values: 0, apply_to: ["string", "number", "datetime"]}], icons: {add_group: "glyphicon glyphicon-plus-sign", add_rule: "glyphicon glyphicon-plus", remove_group: "glyphicon glyphicon-remove", remove_rule: "glyphicon glyphicon-remove", sort: "glyphicon glyphicon-sort"}}, f.prototype.init = function (b) {
        var c = this; 
        this.$el.on("change.queryBuilder", ".rules-group-header input[name$=_cond]", function () {
            var b = a(this);
            b.is(":checked") && (b.parent().addClass("active"), b.parent().siblings().removeClass("active"))
        }), this.$el.on("change.queryBuilder", ".rule-filter-container select[name$=_filter]", function () {
            var b = a(this), d = b.closest(".rule-container");
            queryBuilderEnabled += 1;
            c.updateRuleFilter(d, b.val())
        }), this.$el.on("change.queryBuilder", ".rule-operator-container select[name$=_operator]", function () {
            var b = a(this), d = b.closest(".rule-container");
            queryBuilderEnabled += 1;
            c.updateRuleOperator(d, b.val())
        }), this.$el.on("click.queryBuilder", "[data-add=rule]", function () {
            var b = a(this), d = b.closest(".rules-group-container").find(">.rules-group-body>.rules-list");
            queryBuilderEnabled += 1;
            c.addRule(d)
        }), this.settings.allow_groups && this.$el.on("click.queryBuilder", "[data-add=group]", function () {
            var b = a(this), d = b.closest(".rules-group-container").find(">.rules-group-body>.rules-list");
            queryBuilderEnabled += 1;
            c.addGroup(d)
        }), this.$el.on("click.queryBuilder", "[data-delete=rule]", function () {
            var b = a(this), c = b.closest(".rule-container");
            queryBuilderEnabled -= 1;
            c.remove()
        }), this.$el.on("click.queryBuilder", "[data-delete=group]", function () {
            var b = a(this), d = b.closest(".rules-group-container");
            queryBuilderEnabled -= 1;
            c.deleteGroup(d)
        }), this.settings.sortable && this.initSortable(), this.$el.addClass("query-builder"), b.rules ? this.setRules(b.rules) : this.addGroup(this.$el)
    }, f.prototype.destroy = function () {
        this.status.generatedId && this.$el.removeAttr("id"), this.$el.empty().off("click.queryBuilder change.queryBuilder").removeClass("query-builder").removeData("queryBuilder")
    }, f.prototype.reset = function () {
        this.status.group_id = 1, this.status.rule_id = 0, this.addRule(this.$el.find(">.rules-group-container>.rules-group-body>.rules-list").empty())
    }, f.prototype.clear = function () {
        this.status.group_id = 0, this.status.rule_id = 0, this.$el.empty()
    }, f.prototype.getRules = function () {
        this.clearErrorMarks();
        var b = this.$el.find(">.rules-group-container"), c = this;
        return function d(b) {
            var e = {}, f = b.find(">.rules-group-body>.rules-list>*");
            e.condition = b.find(">.rules-group-header input[name$=_cond]:checked").val(), e.rules = [];
            for (var g = 0, h = f.length; h > g; g++) {
                var i, j = f.eq(g);
                if (j.hasClass("rule-container")) {
                    var k = c.getRuleFilter(j);
                    if ("-1" == k)
                        return c.markRuleAsError(j, !0), c.triggerValidationError("no_filter", j, null, null, null), {};
                    var l = c.getFilterById(k), m = c.getOperatorByType(c.getRuleOperator(j)), n = null;
                    if (0 !== m.accept_values) {
                        n = c.getRuleValue(j, l, m), l.valueParser && (n = l.valueParser.call(this, j, n, l, m));
                        var o = c.validateValue(j, n, l, m);
                        if (o !== !0)
                            return c.markRuleAsError(j, !0), c.triggerValidationError(o, j, n, l, m), {}
                    }
                    i = {id: l.id, field: l.field, type: l.type, input: l.input, operator: m.type, value: n}, e.rules.push(i)
                } else {
                    if (i = d(j), a.isEmptyObject(i))
                        return{};
                    e.rules.push(i)
                }
            }
            return 0 === e.rules.length ? (c.markRuleAsError(b, !0), c.triggerValidationError("empty_group", b, null, null, null), {}) : e
        }(b)
    }, f.prototype.setRules = function (b) {
        this.clear(), b && b.rules && 0 !== b.rules.length || a.error("Incorrect data object passed");
        var c = this.$el, d = this;
        !function e(b, c) {
            var f = d.addGroup(c, !1), g = f.find(">.rules-group-body>.rules-list"), h = f.find(">.rules-group-header input[name$=_cond]");
            void 0 === b.condition && (b.condition = d.settings.default_condition);
            for (var i = 0, j = d.settings.conditions.length; j > i; i++) {
                var k = d.settings.conditions[i];
                h.filter("[value=" + k + "]").prop("checked", b.condition.toUpperCase() == k.toUpperCase())
            }
            h.trigger("change"), a.each(b.rules, function (b, c) {
                if (c.rules && c.rules.length > 0)
                    d.settings.allow_groups ? e(c, g) : a.error("Groups are disabled");
                else {
                    void 0 === c.id && a.error("Missing rule field id"), void 0 === c.value && (c.value = ""), void 0 === c.operator && (c.operator = "equal");
                    var f = d.addRule(g), h = d.getFilterById(c.id), i = d.getOperatorByType(c.operator);
                    f.find(".rule-filter-container select[name$=_filter]").val(c.id).trigger("change"), f.find(".rule-operator-container select[name$=_operator]").val(c.operator).trigger("change"), 0 !== i.accept_values && d.setRuleValue(f, c, h, i), h.onAfterSetValue && h.onAfterSetValue.call(d, f, c.value, h, i)
                }
            })
        }(b, c)
    }, f.prototype.checkFilters = function () {
        var b = [], c = this;
        a.each(this.filters, function (f, g) {
            switch (g.id || a.error("Missing filter id: " + f), -1 != b.indexOf(g.id) && a.error("Filter already defined: " + g.id), b.push(g.id), g.type || a.error("Missing filter type: " + g.id), -1 == d.indexOf(g.type) && a.error("Invalid type: " + g.type), g.input ? "function" != typeof g.input && -1 == e.indexOf(g.input) && a.error("Invalid input: " + g.input) : g.input = "text", g.field || (g.field = g.id), g.label || (g.label = g.field), c.status.has_optgroup |= !!g.optgroup, g.optgroup || (g.optgroup = null), g.type) {
                case"string":
                    g.internalType = "string";
                    break;
                case"integer":
                case"double":
                    g.internalType = "number";
                    break;
                case"date":
                case"time":
                case"datetime":
                    g.internalType = "datetime"
            }
            switch (g.input) {
                case"radio":
                case"checkbox":
                    (!g.values || g.values.length < 1) && a.error("Missing values for filter: " + g.id)
            }
        }), this.status.has_optgroup && this.filters.sort(function (a, b) {
            return null === a.optgroup && null === b.optgroup ? 0 : null === a.optgroup ? 1 : a.optgroup.localeCompare(b.optgroup)
        })
    }, f.prototype.addGroup = function (b, c) {
        var d = this.nextGroupId(), e = d == this.$el_id + "_group_0", f = a(this.template.group.call(this, d, e));
        return b.append(f), this.settings.onAfterAddGroup && this.settings.onAfterAddGroup.call(this, f), (void 0 === c || c === !0) && this.addRule(f.find(">.rules-group-body>.rules-list")), f
    }, f.prototype.deleteGroup = function (b) {
        if (b[0].id != this.$el_id + "_group_0") {
            this.settings.readonly_behavior.delete_group && b.remove();
            var c = this, d = !1;
            b.find(">.rules-group-body>.rules-list>*").each(function () {
                var b = a(this);
                b.hasClass("rule-container") ? b.hasClass("disabled") ? d = !0 : b.remove() : c.deleteGroup(b)
            }), d || b.remove()
        }
    }, f.prototype.addRule = function (b) {
        var c = this.nextRuleId(), d = a(this.template.rule.call(this, c)), e = a(this.getRuleFilterSelect(c));
        return b.append(d), d.find(".rule-filter-container").append(e), a.fn.selectpicker && e.selectpicker({container: "body", style: "btn-inverse btn-xs", width: "auto", showIcon: !1}), this.settings.onAfterAddRule && this.settings.onAfterAddRule.call(this, d), d
    }, f.prototype.createRuleOperators = function (b, c) {
        var d = b.find(".rule-operator-container").empty();
        if (null !== c) {
            var e = this.getOperators(c), f = a(this.getRuleOperatorSelect(b.attr("id"), e));
            d.html(f), b.data("queryBuilder.operator", e[0]), a.fn.selectpicker && f.selectpicker({container: "body", style: "btn-inverse btn-xs", width: "auto", showIcon: !1})
        }
    }, f.prototype.createRuleInput = function (b, c) {
        var d = b.find(".rule-value-container").empty();
        if (null !== c) {
            var e = this.getOperatorByType(this.getRuleOperator(b));
            if (0 !== e.accept_values) {
                for (var f = a(), g = 0; g < e.accept_values; g++) {
                    var h = a(this.getRuleInput(b.attr("id"), c, g));
                    g > 0 && d.append(" , "), d.append(h), f = f.add(h)
                }
                d.show(), c.onAfterCreateRuleInput && c.onAfterCreateRuleInput.call(this, b, c), c.plugin && f[c.plugin](c.plugin_config || {})
            }
        }
    }, f.prototype.updateRuleFilter = function (a, b) {
        var c = "-1" != b ? this.getFilterById(b) : null;
        this.createRuleOperators(a, c), this.createRuleInput(a, c), a.data("queryBuilder.filter", c)
    }, f.prototype.updateRuleOperator = function (a, b) {
        var c = a.find(".rule-value-container"), d = this.getFilterById(this.getRuleFilter(a)), e = this.getOperatorByType(b);
        if (0 === e.accept_values)
            c.hide();
        else {
            c.show();
            var f = a.data("queryBuilder.operator");
            (c.is(":empty") || e.accept_values != f.accept_values) && this.createRuleInput(a, d)
        }
        a.data("queryBuilder.operator", e), d.onAfterChangeOperator && d.onAfterChangeOperator.call(this, a, d, e)
    }, f.prototype.validateValue = function (a, b, c, d) {
        var e, f = c.validation || {};
        if (f.callback)
            return f.callback.call(this, b, c, d, a);
        e = 1 == d.accept_values ? [b] : b;
        for (var g = 0; g < d.accept_values; g++)
            switch (c.input) {
                case"radio":
                    if (void 0 === e[g])
                        return"radio_empty";
                    break;
                case"checkbox":
                    if (0 === e[g].length)
                        return"checkbox_empty";
                    break;
                case"select":
                    if (c.multiple) {
                        if (0 === e[g].length)
                            return"select_empty"
                    } else if (void 0 === e[g])
                        return"select_empty";
                    break;
                case"text":
                default:
                    switch (c.internalType) {
                        case"string":
                            if (void 0 !== f.min) {
                                if (e[g].length < f.min)
                                    return"string_exceed_min_length"
                            } else if (0 === e[g].length)
                                return"string_empty";
                            if (void 0 !== f.max && e[g].length > f.max)
                                return"string_exceed_max_length";
                            if (f.format && !f.format.test(e[g]))
                                return"string_invalid_format";
                            break;
                        case"number":
                            if (isNaN(e[g]))
                                return"number_nan";
                            if ("integer" == c.type) {
                                if (parseInt(e[g]) != e[g])
                                    return"number_not_integer"
                            } else if (parseFloat(e[g]) != e[g])
                                return"number_not_double";
                            if (void 0 !== f.min && e[g] < f.min)
                                return"number_exceed_min";
                            if (void 0 !== f.max && e[g] > f.max)
                                return"number_exceed_max";
                            if (f.step) {
                                var h = e[g] / f.step;
                                if (parseInt(h) != h)
                                    return"number_wrong_step"
                            }
                            break;
                        case"datetime":
                            if (window.moment && f.format) {
                                var i = moment(e[g], f.format);
                                if (!i.isValid())
                                    return"datetime_invalid";
                                if (f.min && i < moment(f.min, f.format))
                                    return"datetime_exceed_min";
                                if (f.max && i > moment(f.max, f.format))
                                    return"datetime_exceed_max"
                            }
                    }
            }
        return!0
    }, f.prototype.markRuleAsError = function (a, b) {
        b ? a.addClass("has-error") : a.removeClass("has-error")
    }, f.prototype.clearErrorMarks = function () {
        this.$el.find(".has-error").removeClass("has-error")
    }, f.prototype.triggerValidationError = function (a, b, c, d, e) {
        d && d.onValidationError && d.onValidationError.call(this, b, a, c, d, e), this.settings.onValidationError && this.settings.onValidationError.call(this, b, a, c, d, e);
        var f = jQuery.Event("validationError.queryBuilder", {error: a, filter: d, operator: e, value: c, targetRule: b[0], builder: this});
        this.$el.trigger(f)
    }, f.prototype.initSortable = function () {
        a.event.props.push("dataTransfer");
        var b, c, d = !1;
        this.$el.on("mousedown", ".drag-handle", function () {
            d = !0
        }), this.$el.on("mouseup", ".drag-handle", function () {
            d = !1
        }), this.$el.on("dragstart", "[draggable]", function (e) {
            e.stopPropagation(), d ? (d = !1, e.dataTransfer.setData("text", "drag"), c = a(e.target), b = a('<div class="rule-placeholder">&nbsp;</div>'), b.css("min-height", c.height()), b.insertAfter(c), setTimeout(function () {
                c.hide()
            }, 0)) : e.preventDefault()
        }), this.$el.on("dragenter", "[draggable]", function (c) {
            c.preventDefault(), c.stopPropagation();
            var d, e = a(c.target);
            return d = e.closest(".rule-container"), d.length ? void b.detach().insertAfter(d) : (d = e.closest(".rules-group-header"), d.length ? (d = e.closest(".rules-group-container"), void b.detach().prependTo(d.find(".rules-list").eq(0))) : (d = e.closest(".rules-group-container"), d.length ? void b.detach().appendTo(d.find(".rules-list").eq(0)) : void 0))
        }), this.$el.on("dragover", "[draggable]", function (a) {
            a.preventDefault(), a.stopPropagation()
        }), this.$el.on("drop", function (b) {
            b.preventDefault(), b.stopPropagation();
            var d, e = a(b.target);
            return d = e.closest(".rule-container"), d.length ? void c.detach().insertAfter(d) : (d = e.closest(".rules-group-header"), d.length ? (d = e.closest(".rules-group-container"), void c.detach().prependTo(d.find(".rules-list").eq(0))) : (d = e.closest(".rules-group-container"), d.length ? void c.detach().appendTo(d.find(".rules-list").eq(0)) : void 0))
        }), this.$el.on("dragend", "[draggable]", function (a) {
            a.preventDefault(), a.stopPropagation(), c.show(), b.remove()
        })
    }, f.prototype.nextGroupId = function () {
        return this.$el_id + "_group_" + this.status.group_id++
    }, f.prototype.nextRuleId = function () {
        return this.$el_id + "_rule_" + this.status.rule_id++
    }, f.prototype.getOperators = function (a) {
        "string" == typeof a && (a = this.getFilterById(a));
        for (var b = [], c = 0, d = this.operators.length; d > c; c++) {
            if (a.operators) {
                if (-1 == a.operators.indexOf(this.operators[c].type))
                    continue
            } else if (-1 == this.operators[c].apply_to.indexOf(a.internalType))
                continue;
            b.push(this.operators[c])
        }
        return a.operators && b.sort(function (b, c) {
            return a.operators.indexOf(b.type) - a.operators.indexOf(c.type)
        }), b
    }, f.prototype.getFilterById = function (a) {
        for (var b = 0, c = this.filters.length; c > b; b++)
            if (this.filters[b].id == a)
                return this.filters[b];
        throw"Undefined filter: " + a
    }, f.prototype.getOperatorByType = function (a) {
        for (var b = 0, c = this.operators.length; c > b; b++)
            if (this.operators[b].type == a)
                return this.operators[b];
        throw"Undefined operator: " + a
    }, f.prototype.getRuleFilter = function (a) {
        return a.find(".rule-filter-container select[name$=_filter]").val()
    }, f.prototype.getRuleOperator = function (a) {
        return a.find(".rule-operator-container select[name$=_operator]").val()
    }, f.prototype.getRuleValue = function (b, c, d) {
        c = c || this.getFilterById(this.getRuleFilter(b)), d = d || this.getOperatorByType(this.getRuleOperator(b));
        for (var e = [], f = [], g = b.find(".rule-value-container"), h = 0; h < d.accept_values; h++) {
            var i = b[0].id + "_value_" + h;
            switch (c.input) {
                case"radio":
                    e.push(g.find("input[name=" + i + "]:checked").val());
                    break;
                case"checkbox":
                    g.find("input[name=" + i + "]:checked").each(function () {
                        f.push(a(this).val())
                    }), e.push(f);
                    break;
                case"select":
                    c.multiple ? (g.find("select[name=" + i + "] option:selected").each(function () {
                        f.push(a(this).val())
                    }), e.push(f)) : e.push(g.find("select[name=" + i + "] option:selected").val());
                    break;
                case"text":
                default:
                    e.push(g.find("input[name=" + i + "]").val())
            }
        }
        return 1 == d.accept_values && (e = e[0]), e
    }, f.prototype.setRuleValue = function (b, c, d, e) {
        d = d || this.getFilterById(this.getRuleFilter(b)), e = e || this.getOperatorByType(this.getRuleOperator(b));
        var f, g = b.find(".rule-value-container");
        f = 1 == e.accept_values ? [c.value] : c.value;
        for (var h = 0; h < e.accept_values; h++) {
            var i = b[0].id + "_value_" + h;
            switch (d.input) {
                case"radio":
                    g.find("input[name=" + i + '][value="' + f[h] + '"]').prop("checked", !0).trigger("change");
                    break;
                case"checkbox":
                    a.isArray(f[h]) || (f[h] = [f[h]]), a.each(f[h], function (a, b) {
                        g.find("input[name=" + i + '][value="' + b + '"]').prop("checked", !0).trigger("change")
                    });
                    break;
                case"select":
                    g.find("select[name=" + i + "]").val(f[h]).trigger("change");
                    break;
                case"text":
                default:
                    g.find("input[name=" + i + "]").val(f[h]).trigger("change")
            }
        }
        c.readonly && (b.find("input, select").prop("disabled", !0), b.addClass("disabled").find("[data-delete=rule]").remove(), this.settings.sortable && !this.settings.readonly_behavior.sortable && b.find(".drag-handle").remove())
    }, f.prototype.getGroupTemplate = function (a, b) {
        var c = '<dl id="' + a + '" class="rules-group-container" ' + (this.settings.sortable ? 'draggable="true"' : "") + '>   <dt class="rules-group-header">     <div class="btn-group pull-right">       <button type="button" class="btn btn-xs btn-success" data-add="rule">         <i class="' + this.settings.icons.add_rule + '"></i> ' + this.lang.add_rule + "       </button>       " + (this.settings.allow_groups ? '<button type="button" class="btn btn-xs btn-success" data-add="group">         <i class="' + this.settings.icons.add_group + '"></i> ' + this.lang.add_group + "       </button>" : "") + "       " + (b ? "" : '<button type="button" class="btn btn-xs btn-danger" data-delete="group">         <i class="' + this.settings.icons.remove_group + '"></i> ' + this.lang.delete_group + "       </button>") + '     </div>     <div class="btn-group">       ' + this.getGroupConditions(a) + "     </div>     " + (this.settings.sortable && !b ? '<div class="drag-handle"><i class="' + this.settings.icons.sort + '"></i></div>' : "") + "   </dt>   <dd class=rules-group-body>     <ul class=rules-list></ul>   </dd> </dl>";
        return c
    }, f.prototype.getGroupConditions = function (a) {
        for (var b = "", c = 0, d = this.settings.conditions.length; d > c; c++) {
            var e = this.settings.conditions[c], f = e == this.settings.default_condition, g = this.lang["condition_" + e.toLowerCase()] || e;
            b += '            <label class="btn btn-xs btn-primary ' + (f ? "active" : "") + '">               <input type="radio" name="' + a + '_cond" value="' + e + '" ' + (f ? "checked" : "") + "> " + g + "             </label>"
        }
        return b
    }, f.prototype.getRuleTemplate = function (a) {
        var b = '<li id="' + a + '" class="rule-container" ' + (this.settings.sortable ? 'draggable="true"' : "") + '>   <div class="rule-header">     <div class="btn-group pull-right">       <button type="button" class="btn btn-xs btn-danger" data-delete="rule">         <i class="' + this.settings.icons.remove_rule + '"></i> ' + this.lang.delete_rule + "       </button>     </div>   </div>   " + (this.settings.sortable ? '<div class="drag-handle"><i class="' + this.settings.icons.sort + '"></i></div>' : "") + '   <div class="rule-filter-container"></div>   <div class="rule-operator-container"></div>   <div class="rule-value-container"></div> </li>';
        return b
    }, f.prototype.getRuleFilterSelect = function (b) {
        var c = null, d = '<select id="' + b + '_filter" name="' + b + '_filter">';
        return d += '<option value="-1">' + this.lang.filter_select_placeholder + "</option>", a.each(this.filters, function (a, b) {
            c != b.optgroup && (null !== c && (d += "</optgroup>"), c = b.optgroup, null !== c && (d += '<optgroup label="' + c + '">')), d += '<option value="' + b.id + '">' + b.label + "</option>"
        }), null !== c && (d += "</optgroup>"), d += "</select>"
    }, f.prototype.getRuleOperatorSelect = function (a, b) {
        for (var c = '<select name="' + a + '_operator">', d = 0, e = b.length; e > d; d++) {
            var f = this.lang["operator_" + b[d].type] || b[d].type;
            c += '<option value="' + b[d].type + '">' + f + "</option>"
        }
        return c += "</select>"
    }, f.prototype.getRuleInput = function (a, b, d) {
        if ("function" == typeof b.input) {
            var e = this.$el.find("#" + a);
            return b.input.call(this, e, b, d)
        }
        var f, g = b.validation || {}, h = a + "_value_" + d, i = "";
        switch (b.input) {
            case"radio":
                f = b.vertical ? " class=block" : "", c(b.values, function (a, b) {
                    i += "<label" + f + '><input type="radio" name="' + h + '" value="' + a + '"> ' + b + "</label> "
                });
                break;
            case"checkbox":
                f = b.vertical ? " class=block" : "", c(b.values, function (a, b) {
                    i += "<label" + f + '><input type="checkbox" name="' + h + '" value="' + a + '"> ' + b + "</label> "
                });
                break;
            case"select":
                i += '<select name="' + h + '"' + (b.multiple ? " multiple" : "") + ">", c(b.values, function (a, b) {
                    i += '<option value="' + a + '"> ' + b + "</option> "
                }), i += "</select>";
                break;
            case"text":
            default:
                switch (b.internalType) {
                    case"number":
                        i += '<div class="ui-widget"><input type="number" id="' + h + '" name="' + h + '"', g.step && (i += ' step="' + g.step + '"'), g.min && (i += ' min="' + g.min + '"'), g.max && (i += ' max="' + g.max + '"'), b.placeholder && (i += ' placeholder="' + b.placeholder + '"'), i += "></div>";
                        break;
                    case"datetime":
                        //TODO
                    case"text":
                    default:
                        i += '<div class="ui-widget"><input type="text" id="' + h + '" name="' + h + '"', b.placeholder && (i += ' placeholder="' + b.placeholder + '"'), i += "></div>";
                }
               
                rules.push(a + "_filter|||" + a + "_operator|||" + h);
        }
        return i
    }, a.fn.queryBuilder = function (b) {
        this.length > 1 && a.error("Unable to initialize on multiple target");
        var c = this.data("queryBuilder"), d = "object" == typeof b && b || {};
        return c || "destroy" != b ? (c || this.data("queryBuilder", new f(this, d)), "string" == typeof b ? c[b].apply(c, Array.prototype.slice.call(arguments, 1)) : this) : this
    }, a.fn.queryBuilder.defaults = {set: function (a) {
            b(!0, f.DEFAULTS, a)
        }, get: function (b) {
            var c = f.DEFAULTS;
            return b && (c = c[b]), a.extend(!0, {}, c)
        }}, a.fn.queryBuilder.constructor = f
}(jQuery), function (a) {
    function b(a) {
        return a
    }
    function c(a, b) {
        switch (b) {
            case"integer":
                return parseInt(a);
            case"double":
                return parseFloat(a);
            default:
                return a
        }
    }
    function d(a) {
        return"string" != typeof a ? a : a.replace(/[\0\n\r\b\\\'\"]/g, function (a) {
            switch (a) {
                case"\x00":
                    return"\\0";
                case"\n":
                    return"\\n";
                case"\r":
                    return"\\r";
                case"\b":
                    return"\\b";
                default:
                    return"\\" + a
            }
        }).replace(/\t/g, "\\t").replace(/\x1a/g, "\\Z")
    }
    a.fn.queryBuilder.defaults.set({sqlOperators: {equal: "= ?", not_equal: "!= ?", "in": {op: "IN(?)", list: !0, sep: ", "}, not_in: {op: "NOT IN(?)", list: !0, sep: ", "}, less: "< ?", less_or_equal: "<= ?", greater: "> ?", greater_or_equal: ">= ?", between: {op: "BETWEEN ?", list: !0, sep: " AND "}, begins_with: {op: "LIKE(?)", fn: function (a) {
                    return a + "%"
                }}, not_begins_with: {op: "NOT LIKE(?)", fn: function (a) {
                    return a + "%"
                }}, contains: {op: "LIKE(?)", fn: function (a) {
                    return"%" + a + "%"
                }}, not_contains: {op: "NOT LIKE(?)", fn: function (a) {
                    return"%" + a + "%"
                }}, ends_with: {op: "LIKE(?)", fn: function (a) {
                    return"%" + a
                }}, not_ends_with: {op: "NOT LIKE(?)", fn: function (a) {
                    return"%" + a
                }}, is_empty: '== ""', is_not_empty: '!= ""', is_null: "IS NULL", is_not_null: "IS NOT NULL"}}), a.extend(a.fn.queryBuilder.constructor.prototype, {getSQL: function (b, e, f) {
            f = void 0 === f ? this.getRules() : f, b = b === !0 || void 0 === b ? "question_mark" : b, e = e || void 0 === e ? "\n" : " ";
            var g = this, h = 1, i = [], j = function k(f) {
                if (f.condition || (f.condition = g.settings.default_condition), -1 === ["AND", "OR"].indexOf(f.condition.toUpperCase()) && a.error("Unable to build SQL query with " + f.condition + " condition"), !f.rules)
                    return"";
                var j = [];
                return a.each(f.rules, function (f, l) {
                    if (l.rules && l.rules.length > 0)
                        j.push("(" + e + k(l) + e + ")" + e);
                    else {
                        var m = g.getSqlOperator(l.operator), n = g.getOperatorByType(l.operator), o = "";
                        m === !1 && a.error("SQL operation unknown for operator " + l.operator), n.accept_values && (l.value instanceof Array ? !m.list && l.value.length > 1 && a.error("Operator " + l.operator + " cannot accept multiple values") : l.value = [l.value], l.value.forEach(function (a, e) {
                            e > 0 && (o += m.sep), "integer" == l.type || "double" == l.type ? a = c(a, l.type) : b || (a = d(a)), a = m.fn(a), b ? (o += "question_mark" == b ? "?" : "$" + h, i.push(a), h++) : ("string" == typeof a && (a = "'" + a + "'"), o += a)
                        })), j.push(l.field + " " + m.op.replace(/\?/, o))
                    }
                }), j.join(" " + f.condition + e)
            }(f);
            return b ? {sql: j, params: i} : {sql: j}
        }, getSqlOperator: function (a) {
            var c = this.settings.sqlOperators[a];
            return void 0 === c ? !1 : ("string" == typeof c && (c = {op: c}), c.fn || (c.fn = b), c.list || (c.list = !1), c.list && !c.sep && (c.sep = ", "), c)
        }})
}(jQuery);