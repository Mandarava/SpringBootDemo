package com.ztc.support;

import org.joda.money.CurrencyUnit;
import org.joda.money.Money;
import org.springframework.core.convert.converter.Converter;
import org.springframework.data.convert.ReadingConverter;

import java.nio.charset.StandardCharsets;

/**
 * Created by zt 2019/3/10 13:36
 */
@ReadingConverter
public class BytesToMoneyConverter implements Converter<byte[], Money> {
    @Override
    public Money convert(byte[] source) {
        String value = new String(source, StandardCharsets.UTF_8);
        return Money.ofMinor(CurrencyUnit.of("CNY"), Long.parseLong(value));
    }
}