package net.hollowbit.archipeloeditor.tools.editortools;

import java.awt.Dimension;
import java.awt.GridBagConstraints;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import net.hollowbit.archipeloeditor.MainEditor;
import net.hollowbit.archipeloeditor.entity.EntityType;
import net.hollowbit.archipeloeditor.objectdefiners.EntityDefiner;
import net.hollowbit.archipeloeditor.objectdefiners.ObjectDefiner.DefinitionCompleteListener;
import net.hollowbit.archipeloeditor.worldeditor.WorldEditor;
import net.hollowbit.archipeloshared.EntitySnapshot;

public class EntityAdderTool extends Tool {
	
	JComboBox<EntityType> entityType;
	
	public EntityAdderTool(MainEditor editor, WorldEditor worldRenderer) {
		super(editor, worldRenderer);
	}

	@Override
	public void addComponents(JPanel panel) {
		JLabel entityTypeLbl = new JLabel("Entity Type:");
		entityTypeLbl.setPreferredSize(new Dimension(194, 15));

		GridBagConstraints gbc_lbl = new GridBagConstraints();
		gbc_lbl.anchor = GridBagConstraints.WEST;
		gbc_lbl.gridx = 0;
		gbc_lbl.gridy = 0;
		panel.add(entityTypeLbl, gbc_lbl);
		
		entityType = new JComboBox<EntityType>();
		entityType.setPreferredSize(new Dimension(194, 15));
		for (EntityType type : EntityType.values())//Add all entity types
			entityType.addItem(type);
		
		GridBagConstraints gbc_list = new GridBagConstraints();
		gbc_list.anchor = GridBagConstraints.WEST;
		gbc_list.gridwidth = 3;
		gbc_list.gridx = 0;
		gbc_list.gridy = 1;
		
		panel.add(entityType, gbc_list);
	}

	@Override
	public void render(SpriteBatch batch) {
		
	}

	@Override
	public void touchDown(float x, float y, int tileX, int tileY, int button) {}

	@Override
	public void touchUp(float x, float y, int tileX, int tileY, int button) {
		if (tileX >= editor.getMap().getMaxTileX() || tileY >= editor.getMap().getMaxTileY() || tileX < editor.getMap().getMinTileX() || tileY < editor.getMap().getMinTileY())
			return;
		
		EntityDefiner entityDefiner = new EntityDefiner(editor, editor.getAssetManager(), (EntityType) entityType.getSelectedItem(), x, y, new DefinitionCompleteListener<EntitySnapshot>() {
			
			public void objectComplete(EntitySnapshot object) {
				//TODO add entity to the map
			};
			
		});
		entityDefiner.open(null);
	}

	@Override
	public void touchDragged(float x, float y, int tileX, int tileY) {}

	@Override
	public void mouseScrolled(int amount) {}

}
