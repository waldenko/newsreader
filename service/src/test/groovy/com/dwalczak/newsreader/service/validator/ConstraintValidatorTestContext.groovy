package com.dwalczak.newsreader.service.validator

import javax.validation.ClockProvider
import javax.validation.ConstraintValidatorContext

class ConstraintValidatorTestContext implements ConstraintValidatorContext {

    def failedValidations = []
    def defaultConstraintViolationDisabled = false

    @Override
    void disableDefaultConstraintViolation() {
        defaultConstraintViolationDisabled = true
    }

    @Override
    String getDefaultConstraintMessageTemplate() {
        return null
    }

    @Override
    ClockProvider getClockProvider() {
        return null
    }

    @Override
    ConstraintValidatorContext.ConstraintViolationBuilder buildConstraintViolationWithTemplate(String messageTemplate) {
        return new ConstraintValidatorContext.ConstraintViolationBuilder() {
            def field
            def nodeBuilderCustomizableContext = new ConstraintValidatorContext.ConstraintViolationBuilder.NodeBuilderCustomizableContext() {
                @Override
                ConstraintValidatorContext.ConstraintViolationBuilder.NodeContextBuilder inIterable() {
                    return null
                }

                @Override
                ConstraintValidatorContext.ConstraintViolationBuilder.NodeBuilderCustomizableContext inContainer(Class<?> containerClass, Integer typeArgumentIndex) {
                    return null
                }

                @Override
                ConstraintValidatorContext.ConstraintViolationBuilder.NodeBuilderCustomizableContext addNode(String name) {
                    return null
                }

                @Override
                ConstraintValidatorContext.ConstraintViolationBuilder.NodeBuilderCustomizableContext addPropertyNode(String name) {
                    return null
                }

                @Override
                ConstraintValidatorContext.ConstraintViolationBuilder.LeafNodeBuilderCustomizableContext addBeanNode() {
                    return null
                }

                @Override
                ConstraintValidatorContext.ConstraintViolationBuilder.ContainerElementNodeBuilderCustomizableContext addContainerElementNode(String name, Class<?> containerType, Integer typeArgumentIndex) {
                    return null
                }

                @Override
                ConstraintValidatorContext addConstraintViolation() {
                    failedValidations.add([field, messageTemplate])
                    return ConstraintValidatorTestContext.this
                }
            }
            @Override
            ConstraintValidatorContext.ConstraintViolationBuilder.NodeBuilderDefinedContext addNode(String name) {
                return null
            }

            @Override
            ConstraintValidatorContext.ConstraintViolationBuilder.NodeBuilderCustomizableContext addPropertyNode(String name) {
                field = name
                nodeBuilderCustomizableContext
            }

            @Override
            ConstraintValidatorContext.ConstraintViolationBuilder.LeafNodeBuilderCustomizableContext addBeanNode() {
                return null
            }

            @Override
            ConstraintValidatorContext.ConstraintViolationBuilder.ContainerElementNodeBuilderCustomizableContext addContainerElementNode(String name, Class<?> containerType, Integer typeArgumentIndex) {
                return null
            }

            @Override
            ConstraintValidatorContext.ConstraintViolationBuilder.NodeBuilderDefinedContext addParameterNode(int index) {
                return null
            }

            @Override
            ConstraintValidatorContext addConstraintViolation() {
                return null
            }
        }
    }

    @Override
    <T> T unwrap(Class<T> type) {
        return null
    }
}
