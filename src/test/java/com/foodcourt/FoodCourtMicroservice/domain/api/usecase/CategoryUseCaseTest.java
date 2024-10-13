package com.foodcourt.FoodCourtMicroservice.domain.api.usecase;

import com.foodcourt.FoodCourtMicroservice.domain.api.impl.CategoryUseCase;
import com.foodcourt.FoodCourtMicroservice.domain.model.Category;
import com.foodcourt.FoodCourtMicroservice.domain.spi.ICategoryPersistencePort;
import com.foodcourt.FoodCourtMicroservice.util.TestConstants;
import com.foodcourt.FoodCourtMicroservice.util.TestDataFactory;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CategoryUseCaseTest {

    @Mock
    private ICategoryPersistencePort categoryPersistencePort;

    @InjectMocks
    private CategoryUseCase categoryUseCase;

    @Test
    @DisplayName(TestConstants.SHOULD_NOT_SAVE_CATEGORY)
    void shouldNotSaveCategoryWhenCategoryAlreadyExists() {

        Category category = TestDataFactory.createDefaultCategory();

        lenient().when(categoryPersistencePort.findCategoryById(category.getId())).thenReturn(Optional.of(category));

        categoryUseCase.saveCategory(category);

        verify(categoryPersistencePort, never()).saveCategory(category);
    }

    @Test
    @DisplayName(TestConstants.SHOULD_SAVE_CATEGORY)
    void shouldSaveCategoryWhenCategoryDoesNotExist() {

        Category category = TestDataFactory.createDefaultCategory();

        lenient().when(categoryPersistencePort.findCategoryById(category.getId())).thenReturn(Optional.empty());

        categoryUseCase.saveCategory(category);

        verify(categoryPersistencePort).saveCategory(category);
    }
}
